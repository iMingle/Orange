/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mingle.orange.distributed.lock.impl;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.mingle.orange.distributed.ZooKeeperConnectionException;
import org.mingle.orange.distributed.lock.DistributedLock;
import org.mingle.orange.distributed.lock.LockingException;
import org.mingle.orange.util.MorePreconditions;
import org.mingle.orange.util.ZooKeeperClient;
import org.mingle.orange.util.ZooKeeperUtils;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author mingle
 */
public class ZookeeperDistributedLock implements DistributedLock {
    private static final Logger LOG = Logger.getLogger(ZookeeperDistributedLock.class.getName());

    private final ZooKeeperClient zkClient;
    private final String lockPath;
    private final ImmutableList<ACL> acl;

    private final AtomicBoolean aborted = new AtomicBoolean(false);
    private CountDownLatch syncPoint;
    private boolean holdsLock = false;
    private String currentId;
    private String currentNode;
    private String watchedNode;
    private LockWatcher watcher;

    /**
     * Equivalent to {@link #ZookeeperDistributedLock(ZooKeeperClient, String, Iterable)} with a default
     * wide open {@code acl} ({@link ZooDefs.Ids#OPEN_ACL_UNSAFE}).
     */
    public ZookeeperDistributedLock(ZooKeeperClient zkClient, String lockPath) {
        this(zkClient, lockPath, ZooDefs.Ids.OPEN_ACL_UNSAFE);
    }

    /**
     * Creates a distributed lock using the given {@code zkClient} to coordinate locking.
     *
     * @param zkClient The ZooKeeper client to use.
     * @param lockPath The path used to manage the lock under.
     * @param acl      The acl to apply to newly created lock nodes.
     */
    public ZookeeperDistributedLock(ZooKeeperClient zkClient, String lockPath, Iterable<ACL> acl) {
        this.zkClient = Preconditions.checkNotNull(zkClient);
        this.lockPath = MorePreconditions.checkNotBlank(lockPath);
        this.acl = ImmutableList.copyOf(acl);
        this.syncPoint = new CountDownLatch(1);
    }

    private synchronized void prepare() throws ZooKeeperConnectionException, InterruptedException, KeeperException {
        ZooKeeperUtils.ensurePath(zkClient, acl, lockPath);
        LOG.log(Level.FINE, "Working with locking path:" + lockPath);

        // Create an EPHEMERAL_SEQUENTIAL node.
        currentNode =
                zkClient.get().create(lockPath + "/member_", null, acl, CreateMode.EPHEMERAL_SEQUENTIAL);

        // We only care about our actual id since we want to compare ourselves to siblings.
        if (currentNode.contains("/")) {
            currentId = currentNode.substring(currentNode.lastIndexOf("/") + 1);
        }
        LOG.log(Level.FINE, "Received ID from zk:" + currentId);
        this.watcher = new LockWatcher();
    }

    @Override
    public synchronized void lock() throws LockingException {
        if (holdsLock) {
            throw new LockingException("Error, already holding a lock. Call unlock first!");
        }
        try {
            prepare();
            watcher.checkForLock();
            syncPoint.await();
            if (!holdsLock) {
                throw new LockingException("Error, couldn't acquire the lock!");
            }
        } catch (InterruptedException e) {
            cancelAttempt();
            throw new LockingException("InterruptedException while trying to acquire lock!", e);
        } catch (KeeperException e) {
            // No need to clean up since the node wasn't created yet.
            throw new LockingException("KeeperException while trying to acquire lock!", e);
        } catch (ZooKeeperConnectionException e) {
            // No need to clean up since the node wasn't created yet.
            throw new LockingException("ZooKeeperConnectionException while trying to acquire lock", e);
        }
    }

    @Override
    public synchronized boolean tryLock(long timeout, TimeUnit unit) {
        if (holdsLock) {
            throw new LockingException("Error, already holding a lock. Call unlock first!");
        }
        try {
            prepare();
            watcher.checkForLock();
            boolean success = syncPoint.await(timeout, unit);
            if (!success) {
                return false;
            }
            if (!holdsLock) {
                throw new LockingException("Error, couldn't acquire the lock!");
            }
        } catch (InterruptedException e) {
            cancelAttempt();
            return false;
        } catch (KeeperException e) {
            // No need to clean up since the node wasn't created yet.
            throw new LockingException("KeeperException while trying to acquire lock!", e);
        } catch (ZooKeeperConnectionException e) {
            // No need to clean up since the node wasn't created yet.
            throw new LockingException("ZooKeeperConnectionException while trying to acquire lock", e);
        }
        return true;
    }

    @Override
    public synchronized void unlock() throws LockingException {
        if (currentId == null) {
            throw new LockingException("Error, neither attempting to lock nor holding a lock!");
        }
        Preconditions.checkNotNull(currentId);
        // Try aborting!
        if (!holdsLock) {
            aborted.set(true);
            LOG.log(Level.INFO, "Not holding lock, aborting acquisition attempt!");
        } else {
            LOG.log(Level.INFO, "Cleaning up this locks ephemeral node.");
            cleanup();
        }
    }

    private synchronized void cancelAttempt() {
        LOG.log(Level.INFO, "Cancelling lock attempt!");
        cleanup();
        // Bubble up failure...
        holdsLock = false;
        syncPoint.countDown();
    }

    private void cleanup() {
        LOG.info("Cleaning up!");
        Preconditions.checkNotNull(currentId);
        try {
            Stat stat = zkClient.get().exists(currentNode, false);
            if (stat != null) {
                zkClient.get().delete(currentNode, ZooKeeperUtils.ANY_VERSION);
            } else {
                LOG.log(Level.WARNING, "Called cleanup but nothing to cleanup!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        holdsLock = false;
        aborted.set(false);
        currentId = null;
        currentNode = null;
        watcher = null;
        syncPoint = new CountDownLatch(1);
    }

    class LockWatcher implements Watcher {

        public synchronized void checkForLock() {
            MorePreconditions.checkNotBlank(currentId);

            try {
                List<String> candidates = zkClient.get().getChildren(lockPath, null);
                ImmutableList<String> sortedMembers = Ordering.natural().immutableSortedCopy(candidates);

                // Unexpected behavior if there are no children!
                if (sortedMembers.isEmpty()) {
                    throw new LockingException("Error, member list is empty!");
                }

                int memberIndex = sortedMembers.indexOf(currentId);

                // If we hold the lock
                if (memberIndex == 0) {
                    holdsLock = true;
                    syncPoint.countDown();
                } else {
                    final String nextLowestNode = sortedMembers.get(memberIndex - 1);
                    LOG.log(Level.INFO, String.format("Current LockWatcher with ephemeral node [%s], is " +
                            "waiting for [%s] to release lock.", currentId, nextLowestNode));

                    watchedNode = String.format("%s/%s", lockPath, nextLowestNode);
                    Stat stat = zkClient.get().exists(watchedNode, this);
                    if (stat == null) {
                        checkForLock();
                    }
                }
            } catch (InterruptedException e) {
                LOG.log(Level.WARNING, String.format("Current LockWatcher with ephemeral node [%s] " +
                        "got interrupted. Trying to cancel lock acquisition.", currentId), e);
                cancelAttempt();
            } catch (KeeperException e) {
                LOG.log(Level.WARNING, String.format("Current LockWatcher with ephemeral node [%s] " +
                        "got a KeeperException. Trying to cancel lock acquisition.", currentId), e);
                cancelAttempt();
            }
        }

        @Override
        public synchronized void process(WatchedEvent event) {
            // this handles the case where we have aborted a lock and deleted ourselves but still have a
            // watch on the nextLowestNode. This is a workaround since ZK doesn't support unsub.
            if (!event.getPath().equals(watchedNode)) {
                LOG.log(Level.INFO, "Ignoring call for node:" + watchedNode);
                return;
            }

            if (event.getType() == Watcher.Event.EventType.None) {
                switch (event.getState()) {
                    case SyncConnected:
                        LOG.info("Reconnected...");
                        break;
                    case Expired:
                        LOG.log(Level.WARNING, String.format("Current ZK session expired![%s]", currentId));
                        cancelAttempt();
                        break;
                }
            } else if (event.getType() == Event.EventType.NodeDeleted) {
                checkForLock();
            } else {
                LOG.log(Level.WARNING, String.format("Unexpected ZK event: %s", event.getType().name()));
            }
        }
    }
}

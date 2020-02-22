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

package org.orange.distributed.zookeeper;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

/**
 * @author mingle
 */
public class ZookeeperTest {
    private static ZkClient zkClient = new ZkClient("127.0.0.1:2181", 2000, 30000, new SerializableSerializer());

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        if (!zkClient.exists("/mingle"))
            zkClient.create("/mingle", "1", CreateMode.PERSISTENT);

        zkClient.subscribeDataChanges("/mingle", new IZkDataListener() {
            @Override public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("DATA CHANGE path: " + dataPath);
                System.out.println(data);
            }

            @Override public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("DELETE path: " + dataPath);
            }
        });

        zkClient.subscribeStateChanges(new IZkStateListener() {
            @Override public void handleStateChanged(Watcher.Event.KeeperState state) throws Exception {
                System.out.println("STATE CHANGE: " + state);
            }

            @Override public void handleNewSession() throws Exception {
                System.out.println("NEW SESSION");
            }

            @Override public void handleSessionEstablishmentError(Throwable error) throws Exception {
                error.printStackTrace();
            }
        });

        zkClient.subscribeChildChanges("/mingle", (parentPath, currentChilds) -> {
            System.out.println("CHILD parentPath: " + parentPath);
            System.out.println(currentChilds);
        });

        latch.await();
    }

    private static class ZookeeperClient {
        public static void main(String[] args) {
            zkClient.writeData("/mingle", "2");
            zkClient.create("/mingle/ch1", "1-1", CreateMode.PERSISTENT);
            zkClient.create("/mingle/ch2", "1-2", CreateMode.PERSISTENT);
        }
    }
}

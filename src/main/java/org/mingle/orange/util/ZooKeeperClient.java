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

package org.mingle.orange.util;

import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.Objects;

/**
 * @author mingle
 */
public class ZooKeeperClient {
    private static String hosts = "localhost:2181";

    /**
     * 连接的超时时间, 毫秒
     */
    private static final int SESSION_TIMEOUT = 5000;

    private volatile static ZooKeeper zookeeper;

    public ZooKeeper get() {
        if (Objects.isNull(zookeeper)) {
            synchronized (ZooKeeperClient.class) {
                if (Objects.isNull(zookeeper))
                    try {
                        zookeeper = new ZooKeeper(hosts, SESSION_TIMEOUT, event -> {
                            System.out.println(event);
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }

        return zookeeper;
    }
}

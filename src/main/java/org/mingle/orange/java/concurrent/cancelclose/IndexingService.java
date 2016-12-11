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

package org.mingle.orange.java.concurrent.cancelclose;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 用毒丸对象关闭服务,必须生产者消费者数量已知的情况下,才可以使用毒丸对象
 * 
 * @author mingle
 */
public class IndexingService {
    private static final int CAPACITY = 1000;
    private static final File POISON = new File("");

    private final CrawlerThread producer = new CrawlerThread();
    private final IndexerThread consumer = new IndexerThread();
    private final BlockingQueue<File> queue;
    private final FileFilter fileFilter;
    private final File root;

    public IndexingService(File root, final FileFilter fileFilter) {
        this.root = root;
        this.queue = new LinkedBlockingQueue<File>(CAPACITY);
        this.fileFilter = new FileFilter() {
            public boolean accept(File f) {
                return f.isDirectory() || fileFilter.accept(f);
            }
        };
    }

    private boolean alreadyIndexed(File f) {
        return false;
    }

    class CrawlerThread extends Thread {
        public void run() {
            try {
                crawl(root);
            } catch (InterruptedException e) { /* fall through */
            } finally {
                while (true) {
                    try {
                        queue.put(POISON);
                        break;
                    } catch (InterruptedException e1) { /* retry */
                    }
                }
            }
        }

        private void crawl(File root) throws InterruptedException {
            File[] entries = root.listFiles(fileFilter);
            if (entries != null) {
                for (File entry : entries) {
                    if (entry.isDirectory())
                        crawl(entry);
                    else if (!alreadyIndexed(entry))
                        queue.put(entry);
                }
            }
        }
    }

    class IndexerThread extends Thread {
        public void run() {
            try {
                while (true) {
                    File file = queue.take();
                    if (file == POISON)
                        break;
                    else
                        indexFile(file);
                }
            } catch (InterruptedException consumed) {
            }
        }

        public void indexFile(File file) {
            /* ... */
        };
    }

    public void start() {
        producer.start();
        consumer.start();
    }

    public void stop() {
        producer.interrupt();
    }

    public void awaitTermination() throws InterruptedException {
        consumer.join();
    }
}

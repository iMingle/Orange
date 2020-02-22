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

package org.orange.util.message;

import com.google.common.collect.Lists;
import org.apache.kafka.clients.consumer.StickyAssignor;
import org.apache.kafka.clients.consumer.internals.AbstractPartitionAssignor;
import org.apache.kafka.clients.consumer.internals.PartitionAssignor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mingle
 */
public class KafkaPartitionAssignorTest {
    public static void main(String[] args) {
//        AbstractPartitionAssignor assignor = new RangeAssignor();
//        AbstractPartitionAssignor assignor = new RoundRobinAssignor();
        AbstractPartitionAssignor assignor = new StickyAssignor();
        Map<String, Integer> partitionsPerTopic = new HashMap<>(4);
        Map<String, PartitionAssignor.Subscription> subscriptions = new HashMap<>(4);
        partitionsPerTopic.put("topic0", 1);
        partitionsPerTopic.put("topic1", 2);
        partitionsPerTopic.put("topic2", 3);

        subscriptions.put("c0", new PartitionAssignor.Subscription(Lists.newArrayList("topic0")));
        subscriptions.put("c1", new PartitionAssignor.Subscription(Lists.newArrayList("topic0", "topic1")));
        subscriptions.put("c2", new PartitionAssignor.Subscription(Lists.newArrayList("topic0", "topic1",
                "topic2")));

        System.out.println(assignor.assign(partitionsPerTopic, subscriptions));
    }
}

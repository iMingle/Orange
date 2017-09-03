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

package org.mingle.orange.args;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author mingle
 */
public class Test {
    private static final SynchronousQueue<Integer> queue = new SynchronousQueue<>();
    private static final int NUM = 4;

    public static void main(String[] args) throws InterruptedException {
        Gson gson = new GsonBuilder().registerTypeAdapter(Double.class, new JsonSerializer<Double>() {
            @Override public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
                if (src == src.longValue())
                    return new JsonPrimitive(src.longValue());
                return new JsonPrimitive(src);
            }
        }).create();
        Map<String, String> map = gson.fromJson("{\"partner_id\":[12, 13],\"return_order_codes\":[\"S000224016\",\"S000208146\"]}", Map.class);
        System.out.println(gson.toJson(map));

        int[] array = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] tmp = new int[NUM];
        sort(array);
        System.out.println(Arrays.toString(array));

        Integer[] arr = new Integer[] {150, 80, 40, 30, 10, 70, 130, 100, 20, 90, 60, 50, 120, 140, 110};
        Collection<Integer> data = Arrays.asList(arr);
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(data);
        System.out.println(priorityQueue);

        Double.valueOf("12.34");
        System.out.println(Double.valueOf(Double.MIN_VALUE));
        System.out.println(Double.MAX_VALUE);
    }

    public static void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if (array[j] < array[j - 1]) {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
    }
}

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
import org.mingle.orange.util.Utils;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;

/**
 * @author mingle
 */
public class Test {
    private static final SynchronousQueue<Integer> queue = new SynchronousQueue<>();

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

        int[] array = {5, 4, 3, 2, 1};
        sort(array);
        System.out.println(Arrays.toString(array));

        // 00000110
        // 00001100
        System.out.println(Integer.MAX_VALUE >> 30);
        System.out.println((5 >> 1) | 5);
        System.out.println(Utils.nextPowerOf2(8));

        Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = new Object[0];
        System.out.println(DEFAULTCAPACITY_EMPTY_ELEMENTDATA.length);

        System.out.println(Integer.toHexString(-1));
        System.out.println(Integer.MAX_VALUE);

        Thread t1 = new Thread(() -> {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t1.sleep(5000);
        queue.put(1);

        System.out.println(123);
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

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

package org.mingle.orange.distributed;

/**
 * @author mingle
 */
public class Retry {
    private static final int MAX_RETRIES = 5;
    private static final int MAX_WAIT_INTERVAL = 100;

    /**
     * 根据重试次数确定等待时长
     * Exponential Backoff
     *
     * @param retryCount
     * @return
     */
    public static long getWaitTimeExp(int retryCount) {
        return ((long) Math.pow(2, retryCount));
    }

    public static void doOperationAndWaitForResult() {
        // Do some asynchronous operation.
        long token = asyncOperation();

        int retries = 0;
        boolean retry = false;

        do {
            // Get the result of the asynchronous operation.
            Results result = getAsyncOperationResult(token);

            if (Results.SUCCESS == result) {
                retry = false;
            } else if ((Results.NOT_READY == result) ||
                    (Results.TOO_BUSY == result) ||
                    (Results.NO_RESOURCE == result) ||
                    (Results.SERVER_ERROR == result)) {
                retry = true;
            } else {
                retry = false;
            }
            if (retry) {
                long waitTime = Math.min(getWaitTimeExp(retries), MAX_WAIT_INTERVAL);
                // Wait for the next Retry.
                try {
                    Thread.sleep(waitTime);
                } catch (InterruptedException e) {

                }
            }
        } while (retry && (retries++ < MAX_RETRIES));
    }

    private static Results getAsyncOperationResult(long token) {
        return null;
    }

    private static long asyncOperation() {
        return 0;
    }
}

/**
 * 根据返回类型选择是否需要重试
 */
enum Results {
    /**
     * 成功
     */
    SUCCESS,
    NOT_READY,
    TOO_BUSY,
    NO_RESOURCE,
    SERVER_ERROR
}

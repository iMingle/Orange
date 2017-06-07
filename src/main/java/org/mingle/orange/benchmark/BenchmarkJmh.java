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

package org.mingle.orange.benchmark;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 利用JMH进行基准测试
 * java -jar target/practice-0.0.1-SNAPSHOT.jar org.mingle.orange.benchmark.BenchmarkJmh
 *
 * @author mingle
 */
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(1)
@Threads(2000)
@Measurement(iterations = 1)
@Warmup(iterations = 1)
@BenchmarkMode(Mode.All)
public class BenchmarkJmh {

    @Setup
    public void setup() {}

    @TearDown
    public void tearDown() {}

    @Benchmark
    public Long httpGetSum() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8080/async/sync");

        try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
            return Long.valueOf(EntityUtils.toString(response.getEntity()));
        }
    }

}

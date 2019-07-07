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

package org.mingle.orange.util.streaming;

import org.apache.beam.sdk.coders.StringUtf8Coder;
import org.apache.beam.sdk.coders.VarIntCoder;
import org.apache.beam.sdk.testing.NeedsRunner;
import org.apache.beam.sdk.testing.PAssert;
import org.apache.beam.sdk.testing.TestPipeline;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.Sum;
import org.apache.beam.sdk.values.PCollection;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Arrays;
import java.util.List;

/**
 * @author mingle
 */
public class BeamUnitTest {
    private static final List<Integer> INPUTS = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    private static final List<String> INPUTS_STRING = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

    @Rule public final transient TestPipeline pipeline = TestPipeline.create();

    @Test
    @Category(NeedsRunner.class)
    public void testFn() {
        PCollection<Integer> input = pipeline.apply(Create.of(INPUTS)).setCoder(VarIntCoder.of());
        PCollection<Integer> output = input.apply(ParDo.of(new EvenNumberFn()));
        PAssert.that(output).containsInAnyOrder(2, 4, 6, 8, 10);
        pipeline.run();
    }

    @Test
    @Category(NeedsRunner.class)
    public void testEndToEnd() {
        PCollection<String> input = pipeline.apply(Create.of(INPUTS_STRING)).setCoder(StringUtf8Coder.of());
        PCollection<Integer> output1 = input.apply(ParDo.of(new ParseIntFn())).apply(ParDo.of(new EvenNumberFn()));
        PAssert.that(output1).containsInAnyOrder(2, 4, 6, 8, 10);
        PCollection<Integer> sum = output1.apply(Sum.integersGlobally());
        PAssert.thatSingleton(sum).isEqualTo(30);
        pipeline.run();
    }

    static class EvenNumberFn extends DoFn<Integer, Integer> {
        @ProcessElement
        public void processElement(@Element Integer in, OutputReceiver<Integer> out) {
            if (in % 2 == 0) {
                out.output(in);
            }
        }
    }

    static class ParseIntFn extends DoFn<String, Integer> {
        @ProcessElement
        public void processElement(@Element String in, OutputReceiver<Integer> out) {
            out.output(Integer.parseInt(in));
        }
    }
}

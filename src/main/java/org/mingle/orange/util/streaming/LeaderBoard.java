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

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.FileIO;
import org.apache.beam.sdk.io.kafka.KafkaIO;
import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.Filter;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.Max;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.SimpleFunction;
import org.apache.beam.sdk.transforms.Values;
import org.apache.beam.sdk.transforms.windowing.AfterProcessingTime;
import org.apache.beam.sdk.transforms.windowing.AfterWatermark;
import org.apache.beam.sdk.transforms.windowing.FixedWindows;
import org.apache.beam.sdk.transforms.windowing.Window;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.Instant;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 游戏排行榜
 *
 * @author mingle
 */
public class LeaderBoard {
    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd-HH-mm")
            .withZone(DateTimeZone.forTimeZone(TimeZone.getTimeZone("Asia/Shanghai")));

    static class UserScoreInfo implements Comparable<UserScoreInfo>, Serializable {
        Integer userId;
        Integer score;
        Long timestamp;

        UserScoreInfo(Integer userId, Integer score, Long timestamp) {
            this.userId = userId;
            this.score = score;
            this.timestamp = timestamp;
        }

        public Integer getUserId() {
            return this.userId;
        }

        public Integer getScore() {
            return this.score;
        }

        public Long getTimestamp() {
            return this.timestamp;
        }

        @Override public int compareTo(UserScoreInfo o) {
            return this.score.compareTo(o.score);
        }
    }

    public interface Options extends PipelineOptions {
        @Default.String("2019-07-21-15-00")
        String getStartBoundary();

        void setStartBoundary(String value);

        @Default.String("2019-07-21-15-30")
        String getEndBoundary();

        void setEndBoundary(String value);
    }

    public static void main(String[] args) {
        long currentTimeMillis = System.currentTimeMillis();
        ThreadLocalRandom randomTimestamp = ThreadLocalRandom.current();
        ThreadLocalRandom randomUserId = ThreadLocalRandom.current();
        ThreadLocalRandom randomScore = ThreadLocalRandom.current();
        /** 是否测试kafka */
        boolean isStreaming = true;

        Options options = PipelineOptionsFactory.fromArgs(args).withValidation().as(Options.class);
        Pipeline pipeline = Pipeline.create(options);
        Pipeline pipelineStreaming = Pipeline.create(options);

        if (!isStreaming) {
            List<String> userScoreInfos = new ArrayList<>();
            String userScore;
            for (int i = 0; i < 1000; i++) {
                userScore = randomUserId.nextInt(100) + "," + randomScore.nextInt(100) + ","
                        + (currentTimeMillis - randomTimestamp.nextLong(1000 * 60 * 60));

                userScoreInfos.add(userScore);
            }


            final Instant startBoundary = new Instant(formatter.parseMillis(options.getStartBoundary()));
            final Instant endBoundary = new Instant(formatter.parseMillis(options.getEndBoundary()));

            pipeline.apply(Create.of(userScoreInfos))
                    .apply("ConvertUserScoreInfo", ParDo.of(new ConvertUserScoreInfoFn()))
                    .apply("FilterStartTime",
                            Filter.by((UserScoreInfo info) -> info.getTimestamp() > startBoundary.getMillis()))
                    .apply("FilterEndTime",
                            Filter.by((UserScoreInfo info) -> info.getTimestamp() < endBoundary.getMillis()))
                    .apply("RetrieveTop100Players", new ExtractUserAndScore())
                    .apply(FileIO.<List<String>>write().via(new CSVSink(Arrays.asList("userId", "score")))
                            .to("/Users/mingle/Downloads")
                            .withPrefix("scoreboard")
                            .withSuffix(".csv"));

            pipeline.run().waitUntilFinish();
        } else {
            pipelineStreaming.apply(KafkaIO.<Long, String>read()
                    .withBootstrapServers("localhost:9092")
                    .withTopic("user_scores")
                    .withKeyDeserializer(LongDeserializer.class)
                    .withValueDeserializer(StringDeserializer.class)
                    .withLogAppendTime()
                    .withoutMetadata()
            ).apply(Values.create())
                    .apply("ConvertUserScoreInfo", ParDo.of(new ConvertUserScoreInfoFn()))
                    .apply("ConfigUserScores", new ConfigUserScores())
                    .apply("RetrieveTop100Players", new ExtractUserAndScore())
                    .apply(FileIO.<List<String>>write().via(new CSVSink(Arrays.asList("userId", "score")))
                            .to("/Users/mingle/Downloads")
                            .withPrefix("scoreboard_kafka")
                            .withSuffix(".csv").withNumShards(1));

            pipelineStreaming.run().waitUntilFinish();
        }
    }

    private static class ConvertUserScoreInfoFn extends DoFn<String, UserScoreInfo> {
        @ProcessElement
        public void processElement(ProcessContext c) {
            List<String> row = Splitter.on(",").splitToList(c.element());

            c.output(new UserScoreInfo(Integer.parseInt(row.get(0)), Integer.parseInt(row.get(1)),
                    Long.parseLong(row.get(2))));
        }
    }

    private static class ExtractUserAndScore extends PTransform<PCollection<UserScoreInfo>,
            PCollection<List<String>>> {
        @Override public PCollection<List<String>> expand(PCollection<UserScoreInfo> input) {
            PCollection<KV<Integer, UserScoreInfo>> userIdMapScore = input.apply(MapElements.via(
                    new SimpleFunction<UserScoreInfo, KV<Integer, UserScoreInfo>>() {
                        @Override public KV<Integer, UserScoreInfo> apply(UserScoreInfo input) {
                            return KV.of(input.getUserId(), input);
                        }
                    }));

            PCollection<KV<Integer, UserScoreInfo>> maxPerKey = userIdMapScore.apply(Max.perKey());

            return maxPerKey.apply(MapElements.via(new SimpleFunction<KV<Integer, UserScoreInfo>, List<String>>() {
                @Override public List<String> apply(KV<Integer, UserScoreInfo> input) {
                    return Lists.newArrayList(input.getKey() + "," + input.getValue().getScore());
                }
            }));
        }
    }

    private static class CSVSink implements FileIO.Sink<List<String>> {
        private String header;
        private PrintWriter writer;

        CSVSink(List<String> colNames) {
            this.header = Joiner.on(",").join(colNames);
        }

        @Override public void open(WritableByteChannel channel) {
            writer = new PrintWriter(Channels.newOutputStream(channel));
            writer.println(header);
        }

        @Override public void write(List<String> element) {
            writer.println(Joiner.on(",").join(element));
        }

        @Override public void flush() {
            writer.flush();
        }
    }

    static class ConfigUserScores extends PTransform<PCollection<UserScoreInfo>, PCollection<UserScoreInfo>> {
        private final Duration FIXED_WINDOW_SIZE = Duration.standardMinutes(5);
        private final Duration ONE_MINUTES = Duration.standardMinutes(1);
        private final Duration ALLOWED_LATENESS_TIME = Duration.standardMinutes(4);

        @Override
        public PCollection<UserScoreInfo> expand(PCollection<UserScoreInfo> infos) {
            return infos.apply(Window.<UserScoreInfo>into(FixedWindows.of(FIXED_WINDOW_SIZE))
                    .triggering(AfterWatermark.pastEndOfWindow()
                            .withEarlyFirings(AfterProcessingTime.pastFirstElementInPane().plusDelayOf(ONE_MINUTES))
                            .withLateFirings(AfterProcessingTime.pastFirstElementInPane().plusDelayOf(ONE_MINUTES)))
                    .withAllowedLateness(ALLOWED_LATENESS_TIME)
                    .accumulatingFiredPanes());
        }
    }
}

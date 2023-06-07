package com.ckckgo;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

public class Kafka2CH {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        KafkaSource<String> source = KafkaSource.<String>builder()
            .setBootstrapServers("localhost:9092")
            .setTopics("myTestTopic")
            .setValueOnlyDeserializer(new SimpleStringSchema())
            .setStartingOffsets(OffsetsInitializer.earliest())
            .build();

        env.fromSource(source, WatermarkStrategy.noWatermarks(), "Kafka Source").addSink(new mClichouseSink());
        env.execute("kafka2CH");
    }

}

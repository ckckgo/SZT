package com.ckckgo;

import java.util.HashMap;
import java.util.Properties;


import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.kafka.sink.KafkaRecordSerializationSchema;
import org.apache.flink.connector.kafka.sink.KafkaSink;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer011;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        System.out.println( "Hello World!" );

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        Properties prop = new Properties();
        prop.setProperty("bootstrap.servers", "localhost:9092");
        DataStream<String> stream = env.addSource(new mRedisSourceFun()).map( x -> {
            Thread.sleep(5000);
            return x;
        });
        FlinkKafkaProducer kafkaSink = new FlinkKafkaProducer<>("myTestTopic", new SimpleStringSchema(), prop);
        stream.addSink(kafkaSink);
        env.execute("redis2kafka");
    }

}

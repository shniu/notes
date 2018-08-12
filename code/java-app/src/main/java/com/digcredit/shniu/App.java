package com.digcredit.shniu;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * Hello world!
 *
 */
public class App
{
    private static final String TOPIC = "test";
    private static final String KEY_SERIALIZER_CLASS = "org.apache.kafka.common.serialization.IntegerSerializer";
    private static final String VALUE_SERIALIZER_CLASS = "org.apache.kafka.common.serialization.StringSerializer";
    private static final String BOOTSTRAP_SERVERS = "192.168.1.5:9092";
    private static final String CLIENT_ID = "demoProducer";

    public static void main( String[] args )
    {
        Properties props = new Properties();
        props.put("bootstrap.servers", BOOTSTRAP_SERVERS);
        props.put("client.id", CLIENT_ID);
        props.put("key.serializer", KEY_SERIALIZER_CLASS);
        props.put("value.serializer", VALUE_SERIALIZER_CLASS);

        KafkaProducer<Integer, String> producer = new KafkaProducer<Integer, String>(props);
        try {
            producer.send(new ProducerRecord<Integer, String>(TOPIC, 100, "hello world")).get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

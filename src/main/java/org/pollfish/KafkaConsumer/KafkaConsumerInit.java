package org.pollfish.KafkaConsumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.pollfish.Properties.KafkaProperties;
import org.pollfish.SerializeDeserializeLoggingRequest.LoggingRequestDeserializer;
import org.pollfish.logging.service.LoggingEvent;

import java.util.Collections;
import java.util.Properties;

/**
 * Created by fmpasios on 15/9/2018.
 */
public class KafkaConsumerInit {


    public static Consumer<String, LoggingEvent> createConsumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                KafkaProperties.KAFKA_SERVERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "LoggingEventConsumer");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                LoggingRequestDeserializer.class.getName());

        final Consumer<String, LoggingEvent> consumer = new KafkaConsumer<>(props);

        consumer.subscribe(Collections.singletonList(KafkaProperties.TOPIC));

        return consumer;
    }



}

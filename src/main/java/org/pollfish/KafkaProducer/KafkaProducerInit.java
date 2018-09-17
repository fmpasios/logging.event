package org.pollfish.KafkaProducer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.pollfish.Properties.KafkaProperties;
import org.pollfish.SerializeDeserializeLoggingRequest.LoggingRequestSerializer;
import org.pollfish.logging.service.LoggingEvent;

import java.util.Properties;

/**
 * Created by fmpasios on 14/9/2018.
 */
public class KafkaProducerInit {


    public static Producer<String, LoggingEvent> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                KafkaProperties.KAFKA_SERVERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "LoggingEventProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                LoggingRequestSerializer.class.getName());

        return new KafkaProducer<>(props);
    }



}

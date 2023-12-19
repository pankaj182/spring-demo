package com.neatcode.kafka.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
class KafkaProducerConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    /**
     * BOOTSTRAP_SERVERS_CONFIG - Host and port on which Kafka is running.
     * KEY_SERIALIZER_CLASS_CONFIG - Serializer class to be used for the key.
     * VALUE_SERIALIZER_CLASS_CONFIG - Serializer class to be used for the value.
     */
    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    /**
     * ProducerFactory is responsible for creating Kafka Producer instances.
     * Producer instances are thread safe.
     * Using a single instance throughout an application context will give higher performance.
     */
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    /**
     * we need a KafkaTemplate, which wraps a Producer instance and
     * provides convenience methods for sending messages to Kafka topics.
     *
     * Consequently, KakfaTemplate instances are also thread safe, and use of one instance is recommended.
     */
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}

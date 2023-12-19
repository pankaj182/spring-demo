package com.neatcode.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * The send API returns a CompletableFuture object.
     * If we want to block the sending thread and get the result about the sent message, we can call the get API of the CompletableFuture object.
     * The thread will wait for the result, but it will slow down the producer.
     *
     * Kafka is a fast-stream processing platform.
     * Therefore, it’s better to handle the results asynchronously so that the subsequent messages do not wait for the result of the previous message.
     * We can do this through a callback:
     */
    public void sendMessage(String topicName, String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + message +
                    "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                    message + "] due to : " + ex.getMessage());
            }
        });
    }
}

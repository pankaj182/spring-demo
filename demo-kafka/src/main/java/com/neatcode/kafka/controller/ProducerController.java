package com.neatcode.kafka.controller;

import com.neatcode.kafka.service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kafka")
public class ProducerController {

    @Autowired
    KafkaProducer kafkaProducer;

    @GetMapping("/{message}")
    public String sendEvent(@PathVariable String message) {
        kafkaProducer.sendMessage("topic_1", message);
        return "Success";
    }
}

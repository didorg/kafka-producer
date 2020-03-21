package com.dido.kafkaproducer.controller;

import com.dido.kafkaproducer.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping(value = "/kafka")
public class ProducerController {

    // Spring’s KafkaTemplate is auto-configured
    private final KafkaTemplate kafkaTemplate;
    private final KafkaTemplate<String, String> stringKafkaTemplate;

    private static final String MESSAGE_TOPIC = "kafka-example";
    private static final String TRANSFER_TOPIC = "kafka-transfer";

    @Autowired
    public ProducerController(KafkaTemplate kafkaTemplate, KafkaTemplate<String, String> stringKafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.stringKafkaTemplate = stringKafkaTemplate;
    }

    @GetMapping(value = "/send_message/{message}")
    public String publish(@PathVariable("message") final String message){

        stringKafkaTemplate.send(MESSAGE_TOPIC, message);

        return "Published successfully";
    }

    @GetMapping(value = "/send_transfer")
    public String sendTransfer(){
        int m = (int) Math.pow(10, 5);
        int from = m + new Random().nextInt(9 * m);
        int to = m + new Random().nextInt(9 * m);

        Transfer transfer = new Transfer(from, to, (long) Math.pow(10,4));
        kafkaTemplate.send(TRANSFER_TOPIC, transfer);

        return "Published successfully";
    }
}

package com.example.bankapi.controllers;

import com.example.bankapi.kafka.KafkaProducer;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    private final KafkaProducer kafkaProducer;

    public KafkaController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/send")
    @Operation(summary = "Отправить сообщение в Kafka")
    public ResponseEntity<String> sendMessage(@RequestBody String message) {
        kafkaProducer.send("bank-events", message);
        return ResponseEntity.ok("Message sent to Kafka: " + message);
    }

    @GetMapping("/ping")
    @Operation(summary = "Ping Kafka controller")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong");
    }
}

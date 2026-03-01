package com.example.bankapi.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
	@KafkaListener(topics = "bank-events", groupId = "bank-group")
	public void listen(String message) {
		System.out.println("Received message: " + message);
	}
}

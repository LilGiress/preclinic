package com.medecineWebApp.Finance_service.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class RestockProducer {
    private final KafkaTemplate<String, RestockEvent> kafkaTemplate;

   //@Value("${kafka.topic.restock}")
    private  String topic;

    public RestockProducer(KafkaTemplate<String, RestockEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendRestockEvent(RestockEvent event) {
        kafkaTemplate.send(topic, event);
    }

}

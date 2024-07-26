package com.maycguides.orderservice.kafka;

import com.maycguides.basedomains.dto.Order;
import com.maycguides.basedomains.dto.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service

public class OrderProducer {
    private NewTopic topic;
    private KafkaTemplate<String, OrderEvent>kafkaTemplate;
    private static final Logger LOGGER= LoggerFactory.getLogger(OrderProducer.class);
    public OrderProducer(KafkaTemplate<String, OrderEvent> kafkaTemplate, NewTopic topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }
    public void sendMessage(OrderEvent event){
        LOGGER.info(String.format("message sent to topic %s",event));
        Message<OrderEvent> message= MessageBuilder.withPayload(event).setHeader(KafkaHeaders.TOPIC,topic.name()).build();
        kafkaTemplate.send(message);
    }

}

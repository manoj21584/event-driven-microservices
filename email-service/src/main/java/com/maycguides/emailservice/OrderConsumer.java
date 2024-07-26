package com.maycguides.emailservice;

import com.maycguides.basedomains.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private static final Logger LOGGER= LoggerFactory.getLogger(OrderConsumer.class);
    @KafkaListener(topics ="${spring.kafka.topic.name}",groupId = "${spring.kafka.consumer.group-id}")
    public void consumeOrder(OrderEvent orderEvent){
        LOGGER.info(String.format("message is received from server in email service %s",orderEvent.toString()));

        //send detail to required email

    }
}

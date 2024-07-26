package com.maycguides.orderservice.controllers;

import com.maycguides.basedomains.dto.Order;
import com.maycguides.basedomains.dto.OrderEvent;
import com.maycguides.orderservice.kafka.OrderProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    private OrderProducer orderProducer;
    @PostMapping("/publish")
    public ResponseEntity<String> sendMessageToTopic(@RequestBody Order order){
        order.setOrderId(UUID.randomUUID().toString());
        OrderEvent orderEvent=new OrderEvent();
        orderEvent.setMessage("order status is in pending state");
        orderEvent.setStatus("pending");
        orderEvent.setOrder(order);
        orderProducer.sendMessage(orderEvent);

        return ResponseEntity.ok("message successfully sent to topic");
    }
}

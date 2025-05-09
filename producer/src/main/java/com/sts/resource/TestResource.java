package com.sts.resource;

import com.sts.event.producer.RabbitMQProducer;
import com.sts.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TestResource {

    @Value("${rabbitmq.exchange.test}")
    private String exchange;

    @Value("${rabbitmq.routing-key.test}")
    private String routingKey;

    private final RabbitMQProducer rabbitMQProducer;

    @PostMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestBody User user) {
        rabbitMQProducer.sendMessage(exchange, routingKey, user);
        return ResponseEntity.ok("Message sent to RabbitMQ ...");
    }

}

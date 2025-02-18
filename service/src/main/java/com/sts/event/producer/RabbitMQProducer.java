package com.sts.event.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(String exchange, String routingKey, Object message) {
        log.info("Message: {} sent to exchange: {} with routing key: {}", message.toString(), exchange, routingKey);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

}

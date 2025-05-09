package com.sts.projection.user.event.handler;

import com.sts.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserWasSaveEventHandler {

    @RabbitListener(queues = {"${rabbitmq.queue.test}"})
    public void consumeMessage(User user) {
        log.info("Received JSON message -> {}", user.toString());
    }

}

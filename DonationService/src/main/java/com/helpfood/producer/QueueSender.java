package com.helpfood.producer;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueueSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    public void send(String json) {
        rabbitTemplate.convertAndSend(this.queue.getName(), json);
    }

    public void sendDonationToUser(String json, Boolean last) {
        // set the header of the "last" property of message
        rabbitTemplate.setBeforePublishPostProcessors(message -> {
            message.getMessageProperties().setHeader("last", last);
            return message;
        });
        rabbitTemplate.convertAndSend("Donation", json);
    }
}

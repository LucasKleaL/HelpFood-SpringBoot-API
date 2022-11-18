package com.helpfood.userservice.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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

    public void sendRequestDonationByUserId(Integer id, String userType) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String message = "userId: " + id + " userType: " + userType;
        rabbitTemplate.convertAndSend("Donation", mapper.writeValueAsString(message));
    }
}

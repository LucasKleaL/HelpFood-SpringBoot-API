package com.helpfood.userservice.producer;

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
        Object message = rabbitTemplate.convertSendAndReceive("User", json);
        System.out.println(message);
    }

    /*
    public void sendRequestDonationByUserId(Integer id, String userType) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String message = "userId: " + id + " userType: " + userType;
        rabbitTemplate.convertAndSend("User", mapper.writeValueAsString(message));
    }
     */
}

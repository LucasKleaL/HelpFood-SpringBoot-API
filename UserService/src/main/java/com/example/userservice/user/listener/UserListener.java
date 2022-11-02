package com.example.userservice.user.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class UserListener {

    @RabbitListener(queues = {"${queue.name}"})
    public void receive(@Payload String json) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        DonationTO donation = mapper.readValue(json, DonationTO.class);
        System.out.println(donation);
    }

}

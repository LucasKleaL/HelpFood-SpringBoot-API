package com.helpfood.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helpfood.donation.service.DonationService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class DonationListener {

    @Autowired
    DonationService donationService;

    /*
    @RabbitListener(queues = {"${queue.name}"})
    public void receive(@Payload String json) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        DonationTO donation = mapper.readValue(json, DonationTO.class);
        System.out.println(donation);
    }

    @RabbitListener(queues = "Donation")
    public void receive(Message message) {
        String msg = new String(message.getBody());
        System.out.println(msg);
    }
    */

    @RabbitListener(queues = "User")
    public void receive(@Payload String json) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        UserTO user = mapper.readValue(json, UserTO.class);
        System.out.println(user.getTipo());
        if (user.getTipo().equals("empresa")) {
            donationService.listByDonorId(user.getId());
        } else {
            donationService.listByReceiverId(user.getId());
        }

    }

}

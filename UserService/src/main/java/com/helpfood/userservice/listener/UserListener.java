package com.helpfood.userservice.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class UserListener {

    private List<DonationTO> donationsTOS;

    /*
    @RabbitListener(queues = {"${queue.name}"})
    public void receive(@Payload String json) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        DonationTO donation = mapper.readValue(json, DonationTO.class);
        System.out.println(donation);
    }
    */

    /*
    @RabbitListener(queues = "User")
    public void receive(Message message) {
        String msg = new String(message.getBody());
        System.out.println(msg);
    }
    */

    @RabbitListener(queues = "Donation")
    public void receive(@Payload String json, @Header(name="last", required = false) Boolean last) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        DonationTO donation = mapper.readValue(json, DonationTO.class);
        this.donationsTOS.add(donation);
        if (last) {
            List<DonationTO> donations = this.donationsTOS;
            this.donationsTOS.clear();
        }
    }

}

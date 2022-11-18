package com.helpfood.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DonationListener {

    /*
    @RabbitListener(queues = {"${queue.name}"})
    public void receive(@Payload String json) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        DonationTO donation = mapper.readValue(json, DonationTO.class);
        System.out.println(donation);
    }
    */

    @RabbitListener(queues = "Donation")
    public void receive(Message message) {
        String msg = new String(message.getBody());
        System.out.println(msg);
    }

}

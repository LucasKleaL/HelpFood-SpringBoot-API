package com.helpfood.userservice.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helpfood.userservice.donation.DonationTO;
import com.helpfood.userservice.user.service.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class UserListener {

    @Autowired
    UserService userService;

    @RabbitListener(queues = "Donation")
    public void receive(@Payload String json, @Header(name="operation", required = false) String operation) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        DonationTO donation = mapper.readValue(json, DonationTO.class);
        if (operation.equals("add")) {
            userService.addDonationId(donation);
        }
        else if (operation.equals("remove")) {
            userService.removeDonationId(donation);
        }
    }

    /*
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
     */

}

package com.helpfood.userservice.listener;

import java.io.Serializable;

public class DonationTO implements Serializable {

    private Integer donationId;
    private Integer userId;

    public Integer getDonationId() {
        return donationId;
    }

    public void setDonationId(Integer donationId) {
        this.donationId = donationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

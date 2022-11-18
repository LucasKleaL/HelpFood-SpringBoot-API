package com.helpfood.listener;

import java.io.Serializable;

public class UserTO implements Serializable {

    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

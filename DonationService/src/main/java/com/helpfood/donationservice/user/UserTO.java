package com.helpfood.donationservice.user;

import com.helpfood.donationservice.donation.entity.Donation;

import javax.persistence.*;
import java.util.List;

public class UserTO {

    private Integer id;

    private String name;

    private String email;

    private String password;

    private Boolean emailVerified;

    private Boolean allowed;

    private String cnpj;

    private String role;

    @ElementCollection
    private List<Integer> donationIds;

    @Transient
    private List<Donation> donations;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public Boolean getAllowed() {
        return allowed;
    }

    public void setAllowed(Boolean allowed) {
        this.allowed = allowed;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Integer> getDonationIds() {
        return donationIds;
    }

    public void setDonationIds(List<Integer> donationIds) {
        this.donationIds = donationIds;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }
}

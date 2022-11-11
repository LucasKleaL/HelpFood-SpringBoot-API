package com.helpfood.donation.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DOACAO")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String Active;
    private String BusinessDonor;
    private String Description;
    private String District;
    private String EmailDonor;
    private String NameDonor;
    private Integer donorId;
    private String Number;
    private String Phone;
    private String Quantity;
    private String Receiver;
    private String Street;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActive() {
        return Active;
    }

    public void setActive(String active) {
        Active = active;
    }

    public String getBusinessDonor() {
        return BusinessDonor;
    }

    public void setBusinessDonor(String businessDonor) {
        BusinessDonor = businessDonor;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getEmailDonor() {
        return EmailDonor;
    }

    public void setEmailDonor(String emailDonor) {
        EmailDonor = emailDonor;
    }

    public Integer getDonorId() {
        return donorId;
    }

    public void setDonorId(Integer donorId) {
        donorId = donorId;
    }

    public String getNameDonor() {
        return NameDonor;
    }

    public void setNameDonor(String nameDonor) {
        NameDonor = nameDonor;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getReceiver() {
        return Receiver;
    }

    public void setReceiver(String receiver) {
        Receiver = receiver;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }
}

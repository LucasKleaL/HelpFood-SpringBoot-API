package com.helpfood.userservice.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.helpfood.userservice.donation.DonationTO;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "EmailVerified", nullable = true)
    private Boolean emailVerified;

    @Column(name = "Allowed", nullable = true)
    private Boolean allowed;

    @Column(name = "Cnpj", nullable = false)
    private String cnpj;

    @Column(name = "Role", nullable = false)
    private String role;

    @Column(name="CountDonations", nullable = false)
    private Integer countDonations;

    @Transient
    private List<DonationTO> donations;

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

    public List<DonationTO> getDonations() {
        return donations;
    }

    public void setDonations(List<DonationTO> donations) {
        this.donations = donations;
    }

    public Integer getCountDonations() {
        return countDonations;
    }

    public void setCountDonations(Integer countDonations) {
        this.countDonations = countDonations;
    }

    public void addDonation() {
        this.countDonations++;
    }

    public void removeDonation() {
        this.countDonations--;
    }
}

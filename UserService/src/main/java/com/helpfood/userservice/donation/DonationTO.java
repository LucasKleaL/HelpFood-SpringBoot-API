package com.helpfood.userservice.donation;

import com.helpfood.userservice.product.ProductTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

public class DonationTO implements Serializable {

    private Integer id;

    private Boolean isActive;

    private String title;

    private String description;

    private Integer donorId;

    private String donorBusinessName;

    private String donorAddress;

    private Integer receiverId;

    private String receiverName;

    private String receiverAddress;

    private String creationDate;

    private String donationDate;

    @ElementCollection
    private List<Integer> productsIds;

    @Transient
    private List<ProductTO> products;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDonorId() {
        return donorId;
    }

    public void setDonorId(Integer donorId) {
        this.donorId = donorId;
    }

    public String getDonorBusinessName() {
        return donorBusinessName;
    }

    public void setDonorBusinessName(String donorBusinessName) {
        this.donorBusinessName = donorBusinessName;
    }

    public String getDonorAddress() {
        return donorAddress;
    }

    public void setDonorAddress(String donorAddress) {
        this.donorAddress = donorAddress;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(String donationDate) {
        this.donationDate = donationDate;
    }

    public List<Integer> getProductsIds() {
        return productsIds;
    }

    public void setProductsIds(List<Integer> productsIds) {
        this.productsIds = productsIds;
    }

    public List<ProductTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductTO> products) {
        this.products = products;
    }
}

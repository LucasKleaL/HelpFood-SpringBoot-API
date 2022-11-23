package com.helpfood.donationservice.donation.entity;
import com.helpfood.donationservice.product.ProductTO;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "DONATIONS")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="IsActive", nullable = false)
    private Boolean isActive;

    @Column(name="Title", nullable = false)
    private String title;

    @Column(name="Description")
    private String description;

    @Column(name="DonorId", nullable = false)
    private Integer donorId;

    @Column(name="DonorBusinessName", nullable = false)
    private String donorBusinessName;

    @Column(name="DonorAddress", nullable = false)
    private String donorAddress;

    @Column(name="ReceiverId")
    private Integer receiverId;

    @Column(name="ReceiverName")
    private String receiverName;

    @Column(name="ReceiverAddress")
    private String receiverAddress;

    @Column(name="CreationDate", nullable = false)
    private String creationDate;

    @Column(name="DonationDate")
    private String donationDate;

    @ElementCollection
    @Column(name="ProductsIds", nullable = false)
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

    public void addDonationProductsId(Integer productId) {
        this.productsIds.add(productId);
    }

    public void removeDonationProductsId(Integer productId) {
        this.productsIds.remove(Integer.valueOf(productId));
    }

    public List<ProductTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductTO> products) {
        this.products = products;
    }

}

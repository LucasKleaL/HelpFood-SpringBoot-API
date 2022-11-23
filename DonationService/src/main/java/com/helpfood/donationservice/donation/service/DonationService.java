package com.helpfood.donationservice.donation.service;

import com.helpfood.donationservice.donation.entity.Donation;
import com.helpfood.donationservice.donation.repository.DonationRepository;
import com.helpfood.donationservice.producer.QueueSender;
import com.helpfood.donationservice.product.FeignProduct;
import com.helpfood.donationservice.product.ProductTO;
import com.helpfood.donationservice.user.FeignUser;
import com.helpfood.donationservice.util.exception.MessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private QueueSender queueSender;

    @Autowired
    private FeignUser feignUser;

    @Autowired
    private FeignProduct feignProduct;

    public Donation save(Donation donation) throws MessageException {
        // Title validation
        if (donation.getTitle() == null || donation.getTitle().equals("")) {
            throw new MessageException("DONATION_ERR001", "Invalid donation data (Title cannot be null).");
        }
        // DonorId validation
        if (donation.getDonorId() == null || donation.getDonorId() == 0) {
            throw new MessageException("DONATION_ERR001", "Invalid donation data (DonorId cannot be null).");
        }
        if (feignUser.findUserById(donation.getDonorId()).getStatusCodeValue() == 404) {
            throw new MessageException("DONATION_ERR002", "The user with id "+donation.getDonorId()+" don't exist.");
        }
        // DonorAddress validation
        if (donation.getDonorAddress() == null || donation.getDonorAddress().equals("")) {
            throw new MessageException("DONATION_ERR001", "Invalid donation data (DonorAddress cannot be null).");
        }
        // ReceiverId validation
        if (donation.getReceiverId() != null) {
            if (feignUser.findUserById(donation.getReceiverId()).getStatusCodeValue() == 404) {
                throw new MessageException("DONATION_ERR002", "The user with id "+donation.getDonorId()+" don't exist.");
            }
        }
        // DonationProductsIds validation
        if (donation.getProductsIds() == null || donation.getProductsIds().isEmpty()) {
            throw new MessageException("DONATION_ERR001", "Invalid donation data (ProductsIds cannot be null).");
        }
        for (Integer productId : donation.getProductsIds()) {
            if (feignProduct.findProductById(productId).getStatusCodeValue() == 404) {
                throw new MessageException("DONATION_ERR002", "The product with id "+productId+" don't exist.");
            }
        }
        // CreationDate assign
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        donation.setCreationDate(formatter.format(date));

        return donationRepository.save(donation);
    }

    public Donation findById(Integer id) {
        Donation donation = donationRepository.findById(id).get();
        List<Integer> productsIds = donation.getProductsIds();
        List<ProductTO> products = new ArrayList<>();
        for (Integer productId : productsIds) {
            ProductTO product = feignProduct.findProductById(productId).getBody();
            products.add(product);
        }
        donation.setProducts(products);
        return donation;
    }

    public void delete(int id) {
        donationRepository.deleteById(id);
    }

    public List<Donation> getAll() {
        List<Donation> donations = donationRepository.findAll();
        for (Donation donation : donations) {
            List<Integer> productsIds = donation.getProductsIds();
            List<ProductTO> products = new ArrayList<>();
            for (Integer productId : productsIds) {
                ProductTO product = feignProduct.findProductById(productId).getBody();
                products.add(product);
            }
            donation.setProducts(products);
        }
        return donations;
    }

    public List<Donation> listByDonorId(Integer donorId) {
        List<Donation> donations = donationRepository.findAllByDonorId(donorId);
        for (Donation donation : donations) {
            List<Integer> productsIds = donation.getProductsIds();
            List<ProductTO> products = new ArrayList<>();
            for (Integer productId : productsIds) {
                ProductTO product = feignProduct.findProductById(productId).getBody();
                products.add(product);
            }
            donation.setProducts(products);
        }
        return donations;
    }

    public List<Donation> listByReceiverId(Integer receiverId) {
        List<Donation> donations = donationRepository.findAllByReceiverId(receiverId);
        for (Donation donation : donations) {
            List<Integer> productsIds = donation.getProductsIds();
            List<ProductTO> products = new ArrayList<>();
            for (Integer productId : productsIds) {
                ProductTO product = feignProduct.findProductById(productId).getBody();
                products.add(product);
            }
            donation.setProducts(products);
        }
        return donations;
    }

    public Donation donate(Integer donationId, Donation updatedDonation) throws MessageException {
        Donation donation = donationRepository.findById(donationId).get();
        // ReceiverId validation
        if (updatedDonation.getReceiverId() == null || updatedDonation.getReceiverId() == 0) {
            throw new MessageException("DONATION_ERR001", "Invalid donation data (ReceiverId cannot be null).");
        }
        // ReceiverName validation
        if (updatedDonation.getReceiverName() == null || updatedDonation.getReceiverName().equals("")) {
            throw new MessageException("DONATION_ERR001", "Invalid donation data (ReceiverName cannot be null).");
        }
        // ReceiverAddress validation
        if (updatedDonation.getReceiverAddress() == null || updatedDonation.getReceiverAddress().equals("")) {
            throw new MessageException("DONATION_ERR001", "Invalid donation data (ReceiverAddress cannot be null).");
        }
        donation.setReceiverId(updatedDonation.getReceiverId());
        donation.setReceiverName(updatedDonation.getReceiverName());
        donation.setReceiverAddress(updatedDonation.getReceiverAddress());
        // DonationDate assign
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        donation.setDonationDate(formatter.format(date));

        return donationRepository.save(donation);
    }

}
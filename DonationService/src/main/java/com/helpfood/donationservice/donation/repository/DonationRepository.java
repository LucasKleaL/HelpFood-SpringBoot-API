package com.helpfood.donationservice.donation.repository;

import com.helpfood.donationservice.donation.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation,Integer> {
    List<Donation> findAllByDonorId(Integer donorId);

    List<Donation> findAllByReceiverId(Integer receiverId);
}
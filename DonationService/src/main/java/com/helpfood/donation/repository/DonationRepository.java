package com.helpfood.donation.repository;

import com.helpfood.donation.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation,Integer> {
    List<Donation> findAllByDonorId(Integer donorId);
}
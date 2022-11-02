package com.helpfood.donation.repository;

import com.helpfood.donation.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<Donation,Integer> {
}
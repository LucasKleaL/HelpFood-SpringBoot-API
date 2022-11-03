package com.helpfood.donation.service;

import com.helpfood.donation.entity.Donation;
import com.helpfood.donation.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    public void salvar(Donation donation) {
        donationRepository.save(donation);
    }

    public Donation findById(Integer id) {
        return donationRepository.findById(id).get();
    }

    public void deletar(int id) {
        donationRepository.deleteById(id);
    }
    public List<Donation> listar() {
        return donationRepository.findAll();
    }

}
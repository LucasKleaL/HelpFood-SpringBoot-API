package com.helpfood.donation.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helpfood.donation.entity.Donation;
import com.helpfood.donation.repository.DonationRepository;
import com.helpfood.producer.QueueSender;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private QueueSender queueSender;

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

    public List<Donation> listByDonorId(Integer donorId) {
        return donationRepository.findAllByDonorId(donorId);
    }

    public List<Donation> listByReceiverId(Integer receiverId) {
        return donationRepository.findAllByReceiverId(receiverId);
    }

}
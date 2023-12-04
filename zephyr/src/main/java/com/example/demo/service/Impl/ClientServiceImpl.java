package com.example.demo.service.Impl;

import com.example.demo.entity.Address;
import com.example.demo.entity.Client;
import com.example.demo.entity.Staff;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Page<Client> getAll(Pageable pageable) {
        return clientRepository.findAllByStatus(1, pageable);
    }

    @Override
    public Page<Client> listDelete(Pageable pageable) {
        return clientRepository.findAllByStatus(0, pageable);
    }

    @Override
    public void add(Client client) {
        clientRepository.save(client);
    }

    @Override
    public void update(Client client, Long id) {
        clientRepository.save(client);
    }

    @Override
    public Client detail(Long id) {

        return clientRepository.getById(id);
    }

    @Override
    public List<Address> findAllById(Long id) {
        return clientRepository.findAllById(id);
    }

    @Override
    public List<Address> findAllByIdAndStatus(Long id) {
        return clientRepository.findAllByIdAndStatus(id);
    }


    @Override
    public String login(String gmail) {
        Client client = clientRepository.findStaffByGmail(gmail);
        if (client == null) {
            return "login/client";
        } else if (gmail.equalsIgnoreCase(client.getGmail())) {
            return "redirect:/zephyr/home";
        } else {
            return "login/client";
        }

    }

    @Override
    public Client detailGmail(String gmail) {
        for (Client client : clientRepository.findAll()) {
            if (client.getGmail().equalsIgnoreCase(gmail)) {
                return client;
            }
        }
        return null;
    }

    @Override
    public Client detailPhoneNumber(String phoneNumber) {
        for (Client client : clientRepository.findAll()) {
            if (client.getPhoneNumber().equalsIgnoreCase(phoneNumber)) {
                return client;
            }
        }
        return null;
    }

    @Override
    public String SignIn(String gmail, String password) {
        Client client = clientRepository.findClientByGmailAndPassword(gmail,password);
        if (client == null) {
            return "login/client";
        } else if (gmail.equalsIgnoreCase(client.getGmail()) && password.equalsIgnoreCase(client.getPassword())) {
            return "redirect:/zephyr/home";
        } else {
            return "login/client";
        }
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public String findMaxCodeClient() {
        return clientRepository.findMaxCodeClient();
    }

    @Override
    public List<Client> findAllByStatus0() {
        return clientRepository.findAllByStatus0();
    }


}

package com.example.demo.service;


import com.example.demo.entity.Address;
import com.example.demo.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientService {


    Page<Client> getAll(Pageable pageable);

    Page<Client> listDelete(Pageable pageable);

    void add(Client client);

    void update(Client client, Long id);

    Client detail(Long id);

    List<Address> findAllById(Long id);

    List<Address> findAllByIdAndStatus(Long id);

    String login(String gmail);

    Client detailGmail(String gmail);

    Client detailPhoneNumber(String phoneNumber);

}

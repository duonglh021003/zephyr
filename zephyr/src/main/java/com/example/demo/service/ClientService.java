package com.example.demo.service;

import com.example.demo.entity.Client;
import com.example.demo.entity.ProductDetail;
import com.example.demo.entity.Ranks;
import com.example.demo.entity.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientService {
    // Serice RANK
    Page<Ranks> getAllRanks(Pageable pageable);

    List<Ranks> getAllRanks();

    Ranks detailRanks(Long idRanks);

    Ranks addRanks(Ranks ranks);

    void deleteRank(Long idRanks);

    // Service Client
    Page<Client> getAllClients(Pageable pageable);

    Client detailClient(Long idClient);

    Client addClient(Client client);

    void deleteClient(Long idClient);
}

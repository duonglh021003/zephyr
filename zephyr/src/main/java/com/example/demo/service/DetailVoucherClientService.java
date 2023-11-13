package com.example.demo.service;

import com.example.demo.entity.DetailVoucherClient;
import com.example.demo.entity.VoucherClient;

import java.util.List;

public interface DetailVoucherClientService {

    List<DetailVoucherClient> findAll();

    void add(DetailVoucherClient detailVoucherClient);

    void delete(Long id);

    void update(DetailVoucherClient detailVoucherClient, Long id);

    List<DetailVoucherClient> findAllByIdClient(Long client);

    DetailVoucherClient detail(Long id);

    DetailVoucherClient detailCode(String code);

    List<DetailVoucherClient> getAll();
}

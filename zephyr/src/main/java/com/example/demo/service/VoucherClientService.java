package com.example.demo.service;

import com.example.demo.entity.VoucherClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VoucherClientService {

    List<VoucherClient> findAll();

    void add(VoucherClient voucherClient);

    void update(VoucherClient voucherClient, Long id);

    void delete(Long id);

    List<VoucherClient> findAllByInRank(Long rank);

    VoucherClient detail(Long id);

    List<VoucherClient> findAllIdClientDuplicate(Long id, Long client);

    Page<VoucherClient> findAllByStatus1(Pageable pageable);

    List<VoucherClient> findAllByStatus0();
}

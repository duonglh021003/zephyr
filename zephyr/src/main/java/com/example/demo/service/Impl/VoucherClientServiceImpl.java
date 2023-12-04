package com.example.demo.service.Impl;

import com.example.demo.entity.Invoice;
import com.example.demo.entity.VoucherClient;
import com.example.demo.repository.VoucherClientRepository;
import com.example.demo.service.VoucherClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherClientServiceImpl implements VoucherClientService {

    @Autowired
    private VoucherClientRepository voucherClientRepository;

    @Override
    public List<VoucherClient> findAll() {
        return voucherClientRepository.findAll();
    }

    @Override
    public void add(VoucherClient voucherClient) {
        voucherClientRepository.save(voucherClient);
    }

    @Override
    public void update(VoucherClient voucherClient, Long id) {
        voucherClientRepository.save(voucherClient);
    }

    @Override
    public void delete(Long id) {
        voucherClientRepository.deleteById(id);
    }

    @Override
    public List<VoucherClient> findAllByInRank(Long rank) {
        return voucherClientRepository.findAllInRank(rank);
    }

    @Override
    public VoucherClient detail(Long id) {
        return voucherClientRepository.getById(id);
    }


    @Override
    public List<VoucherClient> findAllIdClientDuplicate(Long id, Long client) {
        return voucherClientRepository.findAllIdClientDuplicate(id, client);
    }

    @Override
    public Page<VoucherClient> findAllByStatus1(Pageable pageable) {
        return voucherClientRepository.findAllByStatus1(pageable);
    }

    @Override
    public List<VoucherClient> findAllByStatus0() {
        return voucherClientRepository.findAllByStatus0();
    }

    @Override
    public String findMaxCodeVoucherClient() {
        return voucherClientRepository.findMaxCodeVoucherClient();
    }
}

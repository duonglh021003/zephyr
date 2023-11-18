package com.example.demo.service.Impl;

import com.example.demo.entity.DetailVoucherClient;
import com.example.demo.repository.DetailVoucherClientRepository;
import com.example.demo.service.DetailVoucherClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailVoucherClientServiceImpl implements DetailVoucherClientService {

    @Autowired
    private DetailVoucherClientRepository detailVoucherClientRepository;
    @Override
    public List<DetailVoucherClient> findAll() {
        return detailVoucherClientRepository.findAll();
    }

    @Override
    public void add(DetailVoucherClient detailVoucherClient) {
        detailVoucherClientRepository.save(detailVoucherClient);
    }

    @Override
    public void delete(Long id) {
        detailVoucherClientRepository.deleteById(id);
    }

    @Override
    public void update(DetailVoucherClient detailVoucherClient, Long id) {
        detailVoucherClientRepository.save(detailVoucherClient);
    }

    @Override
    public List<DetailVoucherClient> findAllByIdClient(Long client) {
        return detailVoucherClientRepository.findAllIdClient(client);
    }

    @Override
    public DetailVoucherClient detail(Long id) {
        return detailVoucherClientRepository.getById(id);
    }

    @Override
    public DetailVoucherClient detailCode(String code) {
        for (DetailVoucherClient voucherClient : detailVoucherClientRepository.findAll()) {
            if (voucherClient.getCode().equalsIgnoreCase(code)) {
                return voucherClient;
            }
        }
        return null;
    }

    @Override
    public List<DetailVoucherClient> getAll() {
        return detailVoucherClientRepository.findAllByStatus(1);
    }
}

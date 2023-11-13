package com.example.demo.service.Impl;

import com.example.demo.entity.Invoice;
import com.example.demo.entity.Voucher;
import com.example.demo.repository.VoucherRepository;
import com.example.demo.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherServiceImpl implements VoucherService {

    @Autowired
    private VoucherRepository voucherRepository;

    @Override
    public List<Voucher> findAll() {
        return voucherRepository.findAll();
    }

    @Override
    public void add(Voucher voucher) {
        voucherRepository.save(voucher);
    }

    @Override
    public void update(Voucher voucher, Long id) {
        voucherRepository.save(voucher);
    }

    @Override
    public void delete(Long id) {
        voucherRepository.deleteById(id);
    }

    @Override
    public Voucher detailCode(String code) {
        for (Voucher voucher : voucherRepository.findAll()) {
            if (voucher.getCode().equalsIgnoreCase(code)) {
                return voucher;
            }
        }
        return null;
    }
}

package com.example.demo.service;

import com.example.demo.entity.Voucher;

import java.util.List;

public interface VoucherService {

    List<Voucher> findAll();

    void add(Voucher voucher);

    void update(Voucher voucher, Long id);

    void delete(Long id);

    Voucher detailCode(String code);

    Voucher detail(Long id);

    List<Voucher> findAllByPrice(Double price);
}

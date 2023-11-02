package com.example.demo.service;

import com.example.demo.entity.Invoice;

import java.util.List;

public interface InvoiceService {

    List<Invoice> findAll();

    void add(Invoice invoice);

    void update(Invoice invoice, Long id);

    void delete(Long id);

    Invoice detail(Long id);

}

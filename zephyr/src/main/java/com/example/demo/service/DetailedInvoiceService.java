package com.example.demo.service;

import com.example.demo.entity.Address;
import com.example.demo.entity.DetailedInvoice;

import java.util.List;

public interface DetailedInvoiceService {

    List<DetailedInvoice> findAll();

    void add(DetailedInvoice detailedInvoice);

    void update(DetailedInvoice detailedInvoice, Long id);

    void delete(Long id);

    DetailedInvoice detail(Long id);

    List<DetailedInvoice> findAllByIdInvoice(Long id);


}

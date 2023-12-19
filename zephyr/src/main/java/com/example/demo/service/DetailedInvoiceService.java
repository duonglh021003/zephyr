package com.example.demo.service;

import com.example.demo.entity.Address;
import com.example.demo.entity.DetailedInvoice;
import com.example.demo.entity.Invoice;

import java.util.List;

public interface DetailedInvoiceService {

    List<DetailedInvoice> findAll();

    void add(DetailedInvoice detailedInvoice);

    void update(DetailedInvoice detailedInvoice, Long id);

    void delete(Long id);

    DetailedInvoice detail(Long id);

    List<DetailedInvoice> findAllByIdInvoice(Long id);

    List<Double> capitalSumDetailInvoice(Long id);

    DetailedInvoice findAllByIdInvoiceAndProductDetails(Long idInvoice, Long idProductDetail);

}

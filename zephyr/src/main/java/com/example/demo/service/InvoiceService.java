package com.example.demo.service;

import com.example.demo.entity.Invoice;

import java.util.List;

public interface InvoiceService {

    List<Invoice> findAll();

    void add(Invoice invoice);

    void update(Invoice invoice, Long id);

    void delete(Long id);

    Invoice detail(Long id);

    Invoice detailCode(String code);

    List<Invoice> findByInvoiceMax(Long id);


    List<Invoice> findByInvoiceStatusAll(Long id);

    List<Invoice> findByInvoiceStatus1(Long id);

    List<Invoice> findByInvoiceStatus2(Long id);

    List<Invoice> findByInvoiceStatus3(Long id);

    List<Invoice> findByInvoiceStatus4(Long id);

    List<Invoice> findByInvoiceStatus5(Long id);

    List<Invoice> findByInvoiceStatus6(Long id);

    List<Invoice> findByInvoiceStatus7(Long id);

    List<Invoice> findAllByStatus2();

    List<Invoice> findAllByInvoice(Long id);

    List<Invoice> findAllByIdStaff(Long id);

    List<Invoice> findAllByIdStaffStatus5(Long id);

    List<Invoice> findAllByIdStaffStatusAll(Long id);

    List<Integer> findAllStatisticalProductDay();

    List<Double> findAllStatisticalIntoMoneyDAYPresent();

    List<Integer> findAllStatisticalQuantityMONTHPresent();

    List<Double> findAllStatisticalIntoMoneyMONTHPresent();

    List<Double> findAllStatisticalIntoMoneyYEARPresent();

    List<Integer> findAllStatisticalQuantityYEARPresent();
}

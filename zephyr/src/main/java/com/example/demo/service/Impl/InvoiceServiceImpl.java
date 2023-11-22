package com.example.demo.service.Impl;

import com.example.demo.entity.Client;
import com.example.demo.entity.Invoice;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.service.InvoiceService;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public void add(Invoice invoice) {
        invoiceRepository.save(invoice);
    }

    @Override
    public void update(Invoice invoice, Long id) {
        invoiceRepository.save(invoice);
    }

    @Override
    public void delete(Long id) {
        invoiceRepository.deleteById(id);
    }

    @Override
    public Invoice detail(Long id) {
        return invoiceRepository.getById(id);
    }

    @Override
    public Invoice detailCode(String code) {
        for (Invoice invoice : invoiceRepository.findAll()) {
            if (invoice.getCode().equalsIgnoreCase(code)) {
                return invoice;
            }
        }
        return null;
    }

    @Override
    public List<Invoice> findByInvoiceMax(Long id) {
        return invoiceRepository.findByInvoiceMax(id);
    }

    @Override
    public List<Invoice> findAllByStatus0() {
        return invoiceRepository.findAllByStatus0();
    }

    @Override
    public List<Invoice> findAllByStaffStatus0(Long idStaff) {
        return invoiceRepository.findAllByStaffStatus0(idStaff);
    }

    @Override
    public List<Invoice> findByInvoiceStatusAll(Long id) {
        return invoiceRepository.findByInvoiceStatusAll(id);
    }

    @Override
    public List<Invoice> findByInvoiceStatus1(Long id) {
        return invoiceRepository.findByInvoiceStatus1(id);
    }

    @Override
    public List<Invoice> findByInvoiceStatus2(Long id) {
        return invoiceRepository.findByInvoiceStatus2(id);
    }

    @Override
    public List<Invoice> findByInvoiceStatus3(Long id) {
        return invoiceRepository.findByInvoiceStatus3(id);
    }

    @Override
    public List<Invoice> findByInvoiceStatus4(Long id) {
        return invoiceRepository.findByInvoiceStatus4(id);
    }

    @Override
    public List<Invoice> findByInvoiceStatus5(Long id) {
        return invoiceRepository.findByInvoiceStatus5(id);
    }

    @Override
    public List<Invoice> findByInvoiceStatus6(Long id) {
        return invoiceRepository.findByInvoiceStatus6(id);
    }


    @Override
    public List<Invoice> findByInvoiceStatus7(Long id) {
        return invoiceRepository.findByInvoiceStatus7(id);
    }

    @Override
    public List<Invoice> findAllByStatus2() {
        return invoiceRepository.findAllByStatus(2);
    }

    @Override
    public List<Invoice> findAllByInvoice(Long id) {
        return invoiceRepository.findAllByInvoice(id);
    }

    @Override
    public List<Invoice> findAllByIdStaff(Long id) {
        return invoiceRepository.findAllByIdStaff(id);
    }

    @Override
    public List<Invoice> findAllByIdStaffStatus5(Long id) {
        return invoiceRepository.findAllByIdStaffStatus5(id);
    }

    @Override
    public List<Invoice> findAllByIdStaffStatusAll(Long id) {
        return invoiceRepository.findAllByIdStaffStatusAll(id);
    }

    @Override
    public List<Invoice> findAllByIdDetailInvoice(Long id) {
        return invoiceRepository.findAllByIdDetailInvoice(id);
    }


    @Override
    public List<Invoice> findAllStatisticalInvoiceProductDay() {
        return invoiceRepository.findAllStatisticalInvoiceProductDay();
    }

    @Override
    public List<Invoice> findAllStatisticalInvoiceMonth() {
        return invoiceRepository.findAllStatisticalInvoiceMonth();
    }

    @Override
    public List<Invoice> findAllStatisticalInvoiceYear() {
        return invoiceRepository.findAllStatisticalInvoiceYear();
    }

    @Override
    public List<Integer> findAllStatisticalProductDay() {
        return invoiceRepository.findAllStatisticalProductDay();
    }

    @Override
    public List<Double> findAllStatisticalIntoMoneyDAYPresent() {
        return invoiceRepository.findAllStatisticalIntoMoneyDAYPresent();
    }

    @Override
    public List<Integer> findAllStatisticalQuantityMONTHPresent() {
        return invoiceRepository.findAllStatisticalQuantityMONTHPresent();
    }

    @Override
    public List<Double> findAllStatisticalIntoMoneyMONTHPresent() {
        return invoiceRepository.findAllStatisticalIntoMoneyMONTHPresent();
    }

    @Override
    public List<Double> findAllStatisticalIntoMoneyYEARPresent() {
        return invoiceRepository.findAllStatisticalIntoMoneyYEARPresent();
    }

    @Override
    public List<Integer> findAllStatisticalQuantityYEARPresent() {
        return invoiceRepository.findAllStatisticalQuantityYEARPresent();
    }

    @Override
    public List<Double> findAllStatisticalSearchYear(Integer year) {
        return invoiceRepository.findAllStatisticalSearchYear(year);
    }

    @Override
    public List<Invoice> findAllStatisticalInvoiceSearchYear(Integer year) {
        return invoiceRepository.findAllStatisticalInvoiceSearchYear(year);
    }

    @Override
    public List<Integer> findAllStatisticalQuantitySearchYear(Integer year) {
        return invoiceRepository.findAllStatisticalQuantitySearchYear(year);
    }

    @Override
    public List<Double> findAllStatisticalSearchMonth(Integer month, Integer year) {
        return invoiceRepository.findAllStatisticalSearchMonth(month, year);
    }

    @Override
    public List<Invoice> findAllStatisticalInvoiceSearchMonth(Integer month, Integer year) {
        return invoiceRepository.findAllStatisticalInvoiceSearchMonth(month, year);
    }

    @Override
    public List<Integer> findAllStatisticalQuantitySearchMonth(Integer month, Integer year) {
        return invoiceRepository.findAllStatisticalQuantitySearchMonth(month, year);
    }


}

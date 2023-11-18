package com.example.demo.service.Impl;

import com.example.demo.entity.DetailedInvoice;
import com.example.demo.repository.DetailedInvoiceRepository;
import com.example.demo.service.DetailedInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailedInvoiceServiceImpl implements DetailedInvoiceService {

    @Autowired
    private DetailedInvoiceRepository detailedInvoiceRepository;

    @Override
    public List<DetailedInvoice> findAll() {
        return detailedInvoiceRepository.findAll();
    }

    @Override
    public void add(DetailedInvoice detailedInvoice) {
        detailedInvoiceRepository.save(detailedInvoice);
    }

    @Override
    public void update(DetailedInvoice detailedInvoice, Long id) {
        detailedInvoiceRepository.save(detailedInvoice);
    }

    @Override
    public void delete(Long id) {
        detailedInvoiceRepository.deleteById(id);
    }

    @Override
    public DetailedInvoice detail(Long id) {
        return detailedInvoiceRepository.getById(id);
    }

    @Override
    public List<DetailedInvoice> findAllByIdInvoice(Long id) {
        return detailedInvoiceRepository.findAllByIdInvoice(id);
    }

    @Override
    public List<Double> capitalSumDetailInvoice(Long id) {
        return detailedInvoiceRepository.capitalSumDetailInvoice(id);
    }


}

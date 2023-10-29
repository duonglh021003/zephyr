package com.example.demo.service.Impl;

import com.example.demo.entity.ProductDetails;
import com.example.demo.repository.ProductDetailsRepository;
import com.example.demo.service.ProductDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailsServiceImpl implements ProductDetailsService {

    @Autowired
    private ProductDetailsRepository productDetailsRepository;


    @Override
    public Page<ProductDetails> getAll(Pageable pageable) {
        return productDetailsRepository.findAll(pageable);
    }

    @Override
    public List<ProductDetails> findAllByAll(Double minPrice, Double maxPrice, String nameColor, String nameSize) {
        return productDetailsRepository.findAllByAll(minPrice, maxPrice, nameColor, nameSize);
    }
}

package com.example.demo.service;

import com.example.demo.entity.Address;
import com.example.demo.entity.ProductDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductDetailsService {

    Page<ProductDetails> getAll(Pageable pageable);

    List<ProductDetails> findAllByAll(Double minPrice, Double maxPrice, String nameColor, String nameSize);
}

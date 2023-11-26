package com.example.demo.service;

import com.example.demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    List<Product> getAll();

    Page<Product> findAllByStatus1(Pageable pageable);

    List<Product> findAllByStatus0();

    void add(Product product);

    void update(Product product, Long id);

    Product detail(Long id);

    String getNextCode();

    Page<Product> findAllByProductSearch(String inputProduct,Pageable pageable);
}

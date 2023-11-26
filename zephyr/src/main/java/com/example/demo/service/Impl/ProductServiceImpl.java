package com.example.demo.service.Impl;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findAllByStatus1(Pageable pageable) {
        return productRepository.findAllByStatus1(pageable);
    }

    @Override
    public List<Product> findAllByStatus0() {
        return productRepository.findAllByStatus0();
    }

    @Override
    public void add(Product product) {
        productRepository.save(product);
    }

    @Override
    public void update(Product product, Long id) {
        productRepository.save(product);
    }

    @Override
    public Product detail(Long id) {
        return productRepository.getById(id);
    }

    private String incrementCodeOrder(String codeOrder) {
        String prefix = codeOrder.substring(0, 2);
        int number = Integer.parseInt(codeOrder.substring(2));
        number++;
        String nextCodeOrder = String.format("%s%05d", prefix, number);
        return nextCodeOrder;
    }

    @Override
    public String getNextCode() {
        String currentCodeOrder = productRepository.findMaxCodeProduct();
        String nextCodeOrder = incrementCodeOrder(currentCodeOrder);
        return nextCodeOrder;
    }

    @Override
    public Page<Product> findAllByProductSearch(String inputProduct, Pageable pageable) {
        return productRepository.findAllByProductSearch(inputProduct, pageable);
    }
}

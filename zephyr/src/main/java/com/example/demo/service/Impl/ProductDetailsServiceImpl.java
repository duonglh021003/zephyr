package com.example.demo.service.Impl;

import com.example.demo.entity.Product;
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

    @Override
    public ProductDetails detail(Long id) {
        return productDetailsRepository.getById(id);
    }

    @Override
    public void update(ProductDetails productDetails, Long id) {
        productDetailsRepository.save(productDetails);
    }

    @Override
    public List<ProductDetails> getFindAll() {
        return productDetailsRepository.findAll();
    }

    @Override
    public List<ProductDetails> findAllByProductDetail(Long id) {
        return productDetailsRepository.findAllByProductDetail(id);
    }

    @Override
    public List<Product> findAllByProduct(Long id) {
        return productDetailsRepository.findAllByProduct(id);
    }

    @Override
    public List<ProductDetails> findGroupByProduct(Long id) {
        return productDetailsRepository.findGroupByProduct(id);
    }

    @Override
    public Page<ProductDetails> findAllByDisplay(Pageable pageable) {
        return productDetailsRepository.findAllByDisplay(1, pageable);
    }

    @Override
    public List<ProductDetails> findAllByDisplaySell() {
        return productDetailsRepository.findAllByDisplaySell();
    }


    @Override
    public List<ProductDetails> findAllByProductAndColorAndSize(Long product, Long size, Long color) {
        return productDetailsRepository.findAllByProductAndColorAndSize(product, size, color);
    }
}

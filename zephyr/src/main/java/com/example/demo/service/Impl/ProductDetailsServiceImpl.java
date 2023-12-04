package com.example.demo.service.Impl;

import com.example.demo.entity.Color;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductDetails;
import com.example.demo.entity.Size;
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
        return productDetailsRepository.findAllByStatus(1,pageable);
    }

    @Override
    public Page<ProductDetails> findAllOrderByIdProductDetail(Pageable pageable) {
        return productDetailsRepository.findAllOrderByIdProductDetail(pageable);
    }

    @Override
    public List<ProductDetails> findAllOrderByIdProductDetailStatus0() {
        return productDetailsRepository.findAllOrderByIdProductDetailStatus0();
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
    public void add(ProductDetails productDetails) {
        productDetailsRepository.save(productDetails);
    }

    @Override
    public void update(ProductDetails productDetails, Long id) {
        productDetailsRepository.save(productDetails);
    }

    @Override
    public void delete(Long id) {
        productDetailsRepository.deleteById(id);
    }

    @Override
    public List<ProductDetails> getFindAll() {
        return productDetailsRepository.findAll();
    }

    @Override
    public List<ProductDetails> findAllByInventory0() {
        return productDetailsRepository.findAllByInventory0();
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
    public Page<ProductDetails> findAllByProductPage(Pageable pageable) {
        return productDetailsRepository.findAllByProductPage(pageable);
    }

    @Override
    public Page<ProductDetails> findAllByStatus(Pageable pageable) {
        return productDetailsRepository.findAllByStatus(1, pageable);
    }

    @Override
    public List<ProductDetails> findAllByProductList() {
        return productDetailsRepository.findAllByProductList();
    }


    @Override
    public List<ProductDetails> findAllByProductAndColorAndSize(Long product, Long size, Long color) {
        return productDetailsRepository.findAllByProductAndColorAndSize(product, size, color);
    }

    @Override
    public void saveProductDetails(List<ProductDetails> productDetailsList) {
        productDetailsRepository.saveAll(productDetailsList);
    }

    @Override
    public Page<ProductDetails> findAllByProductDetailSearch(String inputProductDetail, Pageable pageable) {
        return productDetailsRepository.findAllByProductDetailSearch(inputProductDetail, pageable);
    }
}

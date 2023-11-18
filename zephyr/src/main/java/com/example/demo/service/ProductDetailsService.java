package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductDetailsService {

    Page<ProductDetails> getAll(Pageable pageable);

    List<ProductDetails> findAllByAll(Double minPrice, Double maxPrice, String nameColor, String nameSize);

    ProductDetails detail(Long id);

    void update(ProductDetails productDetails, Long id);

    List<ProductDetails> getFindAll();

    List<ProductDetails> findAllByProductDetail(Long id);

    List<Product> findAllByProduct(Long id);

    List<ProductDetails> findGroupByProduct(Long id);

    Page<ProductDetails> findAllByDisplay(Pageable pageable);

    List<ProductDetails> findAllByDisplaySell();


    List<ProductDetails> findAllByProductAndColorAndSize(Long product, Long size, Long color);

}

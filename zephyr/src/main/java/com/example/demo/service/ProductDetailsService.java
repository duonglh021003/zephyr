package com.example.demo.service;

import com.example.demo.entity.Address;
import com.example.demo.entity.Client;
import com.example.demo.entity.Color;
import com.example.demo.entity.DetailedShoppingCart;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductDetails;
import com.example.demo.entity.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductDetailsService {

    Page<ProductDetails> getAll(Pageable pageable);

    Page<ProductDetails> findAllOrderByIdProductDetail(Pageable pageable);

    List<ProductDetails> findAllOrderByIdProductDetailStatus0();

    List<ProductDetails> findAllByAll(Double minPrice, Double maxPrice, String nameColor, String nameSize);

    ProductDetails detail(Long id);

    void add(ProductDetails productDetails);

    void update(ProductDetails productDetails, Long id);

    void delete(Long id);

    List<ProductDetails> getFindAll();

    List<ProductDetails> findAllByInventory0();

    List<ProductDetails> findAllByProductDetail(Long id);

    List<Product> findAllByProduct(Long id);

    Page<ProductDetails> findAllByProductPage(Pageable pageable);

    Page<ProductDetails> findAllByStatus(Pageable pageable);

    List<ProductDetails> findAllByProductList();


    List<ProductDetails> findAllByProductAndColorAndSize(Long product, Long size, Long color);

    void saveProductDetails(List<ProductDetails> productDetailsList);

    Page<ProductDetails> findAllByProductDetailSearch(String inputProductDetail, Pageable pageable);

    Page<ProductDetails> findAllByProductDetailSearchProduct(String inputProductDetail, Pageable pageable);
}

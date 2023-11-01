package com.example.demo.service;


import com.example.demo.entity.Client;
import com.example.demo.entity.DetailedShoppingCart;
import com.example.demo.entity.ProductDetails;
import com.example.demo.entity.ShoppingCart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DetailedShoppingCartService {

    List<DetailedShoppingCart> getAllList();

    Page<DetailedShoppingCart> getAll(Pageable pageable);

    DetailedShoppingCart detail(Long id);

    List<DetailedShoppingCart> findAllById(Long id);

    void update(DetailedShoppingCart detailedShoppingCart, Long id);

    void add(DetailedShoppingCart detailedShoppingCart);

    void delete(Long id);

    List<DetailedShoppingCart> findByIdProduct(ShoppingCart shoppingCart, ProductDetails productDetails);

}

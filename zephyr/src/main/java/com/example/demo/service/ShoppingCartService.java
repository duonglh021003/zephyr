package com.example.demo.service;


import com.example.demo.entity.ShoppingCart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ShoppingCartService {

    List<ShoppingCart> getAllList();

    Page<ShoppingCart> getAll(Pageable pageable);

    ShoppingCart detail(Long id);

    List<ShoppingCart> findAllById(Long id);

    List<Double> getTotal(Long id);

    void update(ShoppingCart shoppingCart, Long id);

    void add(ShoppingCart shoppingCart);

}

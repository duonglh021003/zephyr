package com.example.demo.service.Impl;

import com.example.demo.entity.ShoppingCart;
import com.example.demo.repository.ShoppingCartRepository;
import com.example.demo.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Override
    public List<ShoppingCart> getAllList() {
        return shoppingCartRepository.findAll();
    }

    @Override
    public Page<ShoppingCart> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public ShoppingCart detail(Long id) {
        return null;
    }

    @Override
    public List<ShoppingCart> findAllById(Long id) {
        return null;
    }

    @Override
    public List<Double> getTotal(Long id) {
        List<ShoppingCart> shoppingCarts = shoppingCartRepository.findAll();
        List<Double> totalValues = new ArrayList<>();

        for (ShoppingCart shoppingCart : shoppingCarts) {
            totalValues.add(shoppingCart.getTotalShoppingCart());
        }

        return totalValues;
    }

    @Override
    public void update(ShoppingCart shoppingCart, Long id) {
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void add(ShoppingCart shoppingCart) {
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public String findMaxCodeShoppingCart() {
        return shoppingCartRepository.findMaxCodeShoppingCart();
    }


}

package com.example.demo.service.Impl;

import com.example.demo.entity.DetailedShoppingCart;
import com.example.demo.entity.ProductDetails;
import com.example.demo.entity.ShoppingCart;
import com.example.demo.repository.DetailedShoppingCartRepository;
import com.example.demo.service.DetailedShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailedShoppingCartServiceImpl implements DetailedShoppingCartService {

    @Autowired
    private DetailedShoppingCartRepository detailedShoppingCartRepository;

    @Override
    public List<DetailedShoppingCart> getAllList() {
        return detailedShoppingCartRepository.findAll();
    }

    @Override
    public Page<DetailedShoppingCart> getAll(Pageable pageable) {
        return detailedShoppingCartRepository.findAll(pageable);
    }

    @Override
    public DetailedShoppingCart detail(Long id) {
        return detailedShoppingCartRepository.getById(id);
    }

    @Override
    public List<DetailedShoppingCart> findAllById(Long id) {
        return detailedShoppingCartRepository.findAllById(id);
    }

    @Override
    public void update(DetailedShoppingCart detailedShoppingCart, Long id) {
        detailedShoppingCartRepository.save(detailedShoppingCart);
    }

    @Override
    public void add(DetailedShoppingCart detailedShoppingCart) {
        detailedShoppingCartRepository.save(detailedShoppingCart);
    }

    @Override
    public void delete(Long id) {
        detailedShoppingCartRepository.deleteById(id);
    }

    @Override
    public List<DetailedShoppingCart> findByIdProduct(ShoppingCart shoppingCart, ProductDetails productDetails) {
        return detailedShoppingCartRepository.findByIDProduct(shoppingCart,productDetails);
    }
}

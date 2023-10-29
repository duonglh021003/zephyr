package com.example.demo.service;


import com.example.demo.entity.DetailedShoppingCart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DetailedShoppingCartService {

    List<DetailedShoppingCart> getAllList();

    Page<DetailedShoppingCart> getAll(Pageable pageable);

    DetailedShoppingCart detail(Long id);

    List<DetailedShoppingCart> findAllById(Long id);

    void update(DetailedShoppingCart detailedShoppingCart, Long id);


}

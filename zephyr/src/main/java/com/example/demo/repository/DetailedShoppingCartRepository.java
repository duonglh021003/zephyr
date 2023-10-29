package com.example.demo.repository;

import com.example.demo.entity.DetailedShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailedShoppingCartRepository extends JpaRepository<DetailedShoppingCart, Long> {

    @Query("select DISTINCT detailShopping from DetailedShoppingCart detailShopping\n" +
            "inner join  ShoppingCart shopping on detailShopping.shoppingCart.id = shopping.id\n" +
            "where shopping.id = :id")
    List<DetailedShoppingCart> findAllById(@Param("id") Long id);



}

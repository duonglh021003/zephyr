package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select * from product where product_status= 1", nativeQuery = true)
    List<Product> viewCBBProducts();

    @Modifying
    @Transactional
    @Query(value = "update product set product_status= 0 where id= :idProduct", nativeQuery = true)
    void deleteProduct(@Param("idProduct") Long idProduct);
}

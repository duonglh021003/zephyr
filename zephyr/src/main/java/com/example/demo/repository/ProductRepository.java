package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select *\n" +
            "from\n" +
            "product p\n" +
            "where p.product_status = 1\n" +
            "ORDER BY p.id DESC", nativeQuery = true)
    Page<Product> findAllByStatus1(Pageable pageable);

    @Query(value = "select *\n" +
            "from\n" +
            "product p\n" +
            "where p.product_status = 0\n" +
            "ORDER BY p.id DESC", nativeQuery = true)
    List<Product> findAllByStatus0();

    @Query(value = "select max(code) from product", nativeQuery = true)
    String findMaxCodeProduct();

    @Query(value = "select *\n" +
            "from\n" +
            "product p\n" +
            "where p.code like %?1% \n" +
            "or p.product_name like %?1% \n" +
            "or p.date_create like %?1% \n" +
            "or p.date_update like %?1% \n" +
            "or p.user_create like %?1%\n" +
            "or p.user_update like %?1% \n" +
            "ORDER BY p.id DESC", nativeQuery = true)
    Page<Product> findAllByProductSearch(@Param("inputProduct") String inputProduct,
                                         Pageable pageable);



}

package com.example.demo.repository;

import com.example.demo.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
    @Modifying
    @Transactional
    @Query(value = "update product_details set product_details_status= 0 where id= :idProductDetail", nativeQuery = true)
    void deleteProductDetail(@Param("idProductDetail") Long idProductDetail);
}

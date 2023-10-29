package com.example.demo.repository;

import com.example.demo.entity.Address;
import com.example.demo.entity.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long> {

    @Query(value = "select DISTINCT productDetail\n" +
            "select DISTINCT productDetail\n" +
            "from\n" +
            "product_details productDetail \n" +
            "join color c on productDetail.id_color = c.id\n" +
            "join size s on productDetail.id_size = s.id\n" +
            "where \n" +
            "productDetail.price between :minPrice AND :maxPrice\n" +
            "AND c.color_name like CONCAT('%', :nameColor, '%') \n" +
            "AND s.size_name like CONCAT('%', :nameSize, '%')\n",  nativeQuery = true)
    List<ProductDetails> findAllByAll(@Param("minPrice") Double minPrice,
                                      @Param("maxPrice") Double maxPrice,
                                      @Param("nameColor") String nameColor,
                                      @Param("nameSize") String nameSize);


}

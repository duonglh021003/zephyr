package com.example.demo.repository;

import com.example.demo.entity.Address;
import com.example.demo.entity.Client;
import com.example.demo.entity.Color;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductDetails;
import com.example.demo.entity.Size;
import com.example.demo.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
            "AND s.size_name like CONCAT('%', :nameSize, '%')\n", nativeQuery = true)
    List<ProductDetails> findAllByAll(@Param("minPrice") Double minPrice,
                                      @Param("maxPrice") Double maxPrice,
                                      @Param("nameColor") String nameColor,
                                      @Param("nameSize") String nameSize);


    @Query("select DISTINCT productDetail from ProductDetails productDetail\n" +
            "inner join  Product product on productDetail.product.id = product.id\n" +
            "where product.id = :id")
    List<ProductDetails> findAllByProductDetail(@Param("id") Long id);


    @Query("select DISTINCT productDetail\n" +
            "from ProductDetails productDetail join Product p on productDetail.product.id = p.id\n" +
            "join Size s on productDetail.size.id = s.id\n" +
            "join Color c on productDetail.color.id = c.id\n" +
            "where p.id = :product \n" +
            "AND s.id = :size\n" +
            "AND c.id = :color")
    List<ProductDetails> findAllByProductAndColorAndSize(@Param("product") Long product,
                                                         @Param("size") Long size,
                                                         @Param("color") Long color);


    @Query("select DISTINCT product from ProductDetails productDetail\n" +
            "inner join  Product product on productDetail.product.id = product.id\n" +
            "where product.id = :id")
    List<Product> findAllByProduct(@Param("id") Long id);


    @Query(value = "select productDetail.*\n" +
            "from product_details productDetail join product p on productDetail.id_product = p.id\n" +
            "where p.id = ?1 \n" +
            "", nativeQuery = true)
    List<ProductDetails> findGroupByProduct(@Param("product") Long product);

    Page<ProductDetails> findAllByDisplay(int display, Pageable pageable);


    @Query(value = "select pd.*\n" +
            "from product_details pd\n" +
            "where pd.display = 1", nativeQuery = true)
    List<ProductDetails> findAllByDisplaySell();

    Page<ProductDetails> findAllByStatus(int status, Pageable pageable);

    @Query(value = "select *\n" +
            "from\n" +
            "product_details pd\n" +
            "where pd.product_details_status = 1 \n" +
            "ORDER BY pd.id DESC" , nativeQuery = true)
    Page<ProductDetails> findAllOrderByIdProductDetail(Pageable pageable);

    @Query(value = "select *\n" +
            "from\n" +
            "product_details pd\n" +
            "where pd.product_details_status = 0 \n" +
            "ORDER BY pd.id DESC" , nativeQuery = true)
    List<ProductDetails> findAllOrderByIdProductDetailStatus0();

    @Query(value = "select *\n" +
            "from\n" +
            "product_details pd\n" +
            "where pd.inventory = 0" , nativeQuery = true)
    List<ProductDetails> findAllByInventory0();

}

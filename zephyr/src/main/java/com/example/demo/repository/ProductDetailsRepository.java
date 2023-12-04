package com.example.demo.repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductDetails;
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
            "AND s.id = :size \n" +
            "AND c.id = :color")
    List<ProductDetails> findAllByProductAndColorAndSize(@Param("product") Long product,
                                                         @Param("size") Long size,
                                                         @Param("color") Long color);


    @Query("select DISTINCT product from ProductDetails productDetail\n" +
            "inner join  Product product on productDetail.product.id = product.id\n" +
            "where product.id = :id")
    List<Product> findAllByProduct(@Param("id") Long id);


    @Query(value = "select pd.id, pd.images, pd.describe, pd.inventory, pd.import_price, pd.price,\n" +
            "pd.date_create, pd.date_update, pd.user_create, pd.user_update, pd.product_details_status,\n" +
            "pd.id_product, pd.id_origin, pd.id_color, pd.id_size\n" +
            "from product_details pd inner join ( \n" +
            "select id_product, max(id) as MaxId \n" +
            "from product_details \n" +
            "group by id_product) temp \n" +
            "on pd.id_product = temp.id_product and pd.id = temp.MaxId \n" +
            "inner join product on pd.id_product = product.id \n" +
            "order by pd.id_product DESC", nativeQuery = true)
    Page<ProductDetails> findAllByProductPage(Pageable pageable);

    @Query(value = "select *\n" +
            "from ( select *, ROW_NUMBER() OVER (\n" +
            "PARTITION BY id_product \n" +
            "ORDER BY id_product) AS row_num \n" +
            "from product_details ) as subquery\n" +
            "where row_num =1\n" +
            "ORDER BY subquery.id DESC", nativeQuery = true)
    List<ProductDetails> findAllByProductList();

    Page<ProductDetails> findAllByStatus(int status, Pageable pageable);

    @Query(value = "select *\n" +
            "from\n" +
            "product_details pd\n" +
            "where pd.product_details_status = 1 \n" +
            "ORDER BY pd.id DESC", nativeQuery = true)
    Page<ProductDetails> findAllOrderByIdProductDetail(Pageable pageable);

    @Query(value = "select *\n" +
            "from\n" +
            "product_details pd\n" +
            "where pd.product_details_status = 0 \n" +
            "ORDER BY pd.id DESC", nativeQuery = true)
    List<ProductDetails> findAllOrderByIdProductDetailStatus0();

    @Query(value = "select *\n" +
            "from\n" +
            "product_details pd\n" +
            "where pd.inventory = 0", nativeQuery = true)
    List<ProductDetails> findAllByInventory0();

    @Query(value = "select pd.id, pd.images, pd.describe, pd.inventory, pd.import_price, pd.price,\n" +
            "pd.date_create, pd.date_update, pd.user_create, pd.user_update, pd.product_details_status,\n" +
            "pd.id_product, pd.id_origin, pd.id_color, pd.id_size\n" +
            "from\n" +
            "product_details pd join product p on pd.id_product = p.id\n" +
            "join origin o on pd.id_origin = o.id \n" +
            "join color cl on pd.id_color = cl.id\n" +
            "join size s on pd.id_size = s.id \n" +
            "where pd.import_price like %?1% \n" +
            "or pd.price like %?1% \n" +
            "or pd.date_create like %?1% \n" +
            "or pd.date_update like %?1% \n" +
            "or pd.user_create like %?1%\n" +
            "or pd.user_update like %?1% \n" +
            "or p.product_name like %?1% \n" +
            "or o.origin_name like %?1% \n" +
            "or cl.color_name like %?1% \n" +
            "or s.size_name like %?1% \n" +
            "ORDER BY p.id DESC", nativeQuery = true)
    Page<ProductDetails> findAllByProductDetailSearch(@Param("inputProductDetail") String inputProductDetail,
                                         Pageable pageable);
}

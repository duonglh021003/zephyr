package com.example.demo.repository;

import com.example.demo.entity.DetailedShoppingCart;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductDetails;
import com.example.demo.entity.ShoppingCart;
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


    @Query("select DISTINCT detailShopping from DetailedShoppingCart detailShopping\n" +
            "where detailShopping.shoppingCart = :idShopping " +
            "AND detailShopping.productDetails = :idProductdetail")
    List<DetailedShoppingCart> findByIDProduct(@Param("idShopping") ShoppingCart idShopping,
                                               @Param("idProductdetail")ProductDetails idProductdetail);


    @Query(value = "select shoppingDetail.*\n" +
            "from\n" +
            "shopping_cart shopping join client c on shopping.id = c.id_shopping_cart\n" +
            "join detailed_shopping_cart shoppingDetail on shopping.id = shoppingDetail.id_shopping_cart\n" +
            "where c.id = ?1", nativeQuery = true)
    List<DetailedShoppingCart> findAllShoppingDetail(@Param("id") Long id);

}

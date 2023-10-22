package com.example.demo.service;

import com.example.demo.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Staff detailStaffByPhoneNumberAndPassword(String phoneNumber, String password);

    // Service Sản Phẩm
    Page<Product> getAllProducts(Pageable pageable);

    List<Product> getAllProduct1();

    Product detailProduct(Long idProduct);

    Product addProduct(Product product);

    void deleteProduct(Long idProduct);

    // Service Color
    Page<Color> getAllColors(Pageable pageable);

    List<Color> getAllColors1();

    Color detailColor(Long idColor);

    Color addColor(Color color);

    void deleteColor(Long idColor);

    // Service Origin
    Page<Origin> getAllOrigins(Pageable pageable);

    List<Origin> getAllOrigins1();

    Origin detailOrigin(Long idOrigin);

    Origin addOrigin(Origin origin);

    void deleteOrigin(Long idOrigin);

    // Serice Size
    Page<Size> getAllSizes(Pageable pageable);

    List<Size> getAllSizes1();

    Size detailSize(Long idSize);

    Size addSize(Size size);

    void deleteSize(Long idSize);

    // Service ProductDetail
    Page<ProductDetail> getAllProductDetails(Pageable pageable);

    ProductDetail detailProductDetail(Long idProductDetail);

    ProductDetail addProductDetail(ProductDetail productDetail);

    void deleteProductDetail(Long idProductDetail);
}

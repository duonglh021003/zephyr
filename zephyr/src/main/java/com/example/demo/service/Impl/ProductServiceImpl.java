package com.example.demo.service.Impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    StaffRepository staffRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ColorRepository colorRepository;

    @Autowired
    OriginRepository originRepository;

    @Autowired
    SizeRepository sizeRepository;

    @Autowired
    ProductDetailRepository productDetailRepository;

    @Override
    public Staff detailStaffByPhoneNumberAndPassword(String phoneNumber, String password) {
        return staffRepository.findStaffByPhoneNumberAndPassword(phoneNumber, password);
    }

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public List<Product> getAllProduct1() {
        return productRepository.viewCBBProducts();
    }

    @Override
    public Product detailProduct(Long idProduct) {
        return productRepository.findById(idProduct).orElse(null);
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long idProduct) {
        productRepository.deleteProduct(idProduct);
    }

    @Override
    public Page<Color> getAllColors(Pageable pageable) {
        return colorRepository.findAll(pageable);
    }

    @Override
    public List<Color> getAllColors1() {
        return colorRepository.viewCBBColors();
    }

    @Override
    public Color detailColor(Long idColor) {
        return colorRepository.findById(idColor).orElse(null);
    }

    @Override
    public Color addColor(Color color) {
        return colorRepository.save(color);
    }

    @Override
    public void deleteColor(Long idColor) {
        colorRepository.deleteColor(idColor);
    }

    @Override
    public Page<Origin> getAllOrigins(Pageable pageable) {
        return originRepository.findAll(pageable);
    }

    @Override
    public List<Origin> getAllOrigins1() {
        return originRepository.viewCBBOrigins();
    }

    @Override
    public Origin detailOrigin(Long idOrigin) {
        return originRepository.findById(idOrigin).orElse(null);
    }

    @Override
    public Origin addOrigin(Origin origin) {
        return originRepository.save(origin);
    }

    @Override
    public void deleteOrigin(Long idOrigin) {
        originRepository.deleteOrigin(idOrigin);
    }

    @Override
    public Page<Size> getAllSizes(Pageable pageable) {
        return sizeRepository.findAll(pageable);
    }

    @Override
    public List<Size> getAllSizes1() {
        return sizeRepository.viewCBBSizes();
    }

    @Override
    public Size detailSize(Long idSize) {
        return sizeRepository.findById(idSize).orElse(null);
    }

    @Override
    public Size addSize(Size size) {
        return sizeRepository.save(size);
    }

    @Override
    public void deleteSize(Long idSize) {
        sizeRepository.deleteSize(idSize);
    }

    @Override
    public Page<ProductDetail> getAllProductDetails(Pageable pageable) {
        return productDetailRepository.findAll(pageable);
    }

    @Override
    public ProductDetail detailProductDetail(Long idProductDetail) {
        return productDetailRepository.findById(idProductDetail).orElse(null);
    }

    @Override
    public ProductDetail addProductDetail(ProductDetail productDetail) {
        return productDetailRepository.save(productDetail);
    }

    @Override
    public void deleteProductDetail(Long idProductDetail) {
        productDetailRepository.deleteProductDetail(idProductDetail);
    }
}

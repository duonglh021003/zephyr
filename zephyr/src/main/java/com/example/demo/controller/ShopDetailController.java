package com.example.demo.controller;

import com.example.demo.entity.Client;
import com.example.demo.entity.Color;
import com.example.demo.entity.DetailedShoppingCart;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductDetails;
import com.example.demo.entity.Size;
import com.example.demo.repository.ProductDetailsRepository;
import com.example.demo.service.DetailedShoppingCartService;
import com.example.demo.service.ProductDetailsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(value = "/zephyr/shop")
public class ShopDetailController {

    @Autowired
    private ProductDetailsService productDetailsService;

    @Autowired
    private DetailedShoppingCartService detailedShoppingCartService;

    @GetMapping("/shop-detail")
    public String index(@RequestParam("id") Long id,
                        Model model
    ) {

        ProductDetails idProductDetails = productDetailsService.detail(id);
        List<ProductDetails> list = productDetailsService.findAllByProductDetail(id);

        System.out.println("ccccccccccccccccccc     " + productDetailsService.findAllByProduct(id));
        System.out.println("bbbbbbbbbbbbbbbb        " + list);

        for (ProductDetails productDetails : list) {


            model.addAttribute("listProductDetails", productDetailsService.getFindAll());
            model.addAttribute("images", productDetails.getImages());
            model.addAttribute("nameProduct", productDetails.getProduct().getName());
            model.addAttribute("price", productDetails.getPrice());
            model.addAttribute("listSizeShopDetail", productDetailsService.findAllByProductDetail(id));

            model.addAttribute("listProductShopDetail", productDetailsService.findAllByProduct(id));
            model.addAttribute("listColorShopDetail", productDetailsService.findAllByProductDetail(id));


        }


        model.addAttribute("viewClient", "/WEB-INF/view/include/shop-detail.jsp");

        return "layout/client";
    }

    @GetMapping("/shop-detail/add")
    public String demo(@RequestParam("product") Long idProduct,
                       @RequestParam("size") Long idSize,
                       @RequestParam("color") Long idColor,
                       HttpSession session,
                       Model model) {

        LocalDate localDate = LocalDate.now();
        List<ProductDetails> list = productDetailsService.findAllByProductAndColorAndSize(idProduct, idSize, idColor);
        Client client = (Client) session.getAttribute("clientSession");


        for (ProductDetails productDetails : list) {
            DetailedShoppingCart detailedShoppingCart = DetailedShoppingCart.builder()
                    .quantity(1)
                    .unitPrice(productDetails.getPrice())
                    .dateCreate(localDate)
                    .status(1)
                    .shoppingCart(client.getShoppingCart())
                    .productDetails(productDetails)
                    .build();
            detailedShoppingCartService.add(detailedShoppingCart);
        }

        return "redirect:/zephyr/shop";
    }
}

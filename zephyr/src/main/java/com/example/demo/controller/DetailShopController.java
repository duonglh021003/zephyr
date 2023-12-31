package com.example.demo.controller;


import com.example.demo.entity.Client;
import com.example.demo.entity.DetailedInvoice;
import com.example.demo.entity.DetailedShoppingCart;
import com.example.demo.entity.ProductDetails;
import com.example.demo.service.DetailedInvoiceService;
import com.example.demo.service.DetailedShoppingCartService;
import com.example.demo.service.ProductDetailsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/zephyr/shop")
public class DetailShopController {

    @Autowired
    private ProductDetailsService productDetailsService;

    @Autowired
    private DetailedShoppingCartService detailedShoppingCartService;

    @Autowired
    private DetailedInvoiceService detailedInvoiceService;

    @GetMapping("/shop-detail")
    public String index(@RequestParam("id") Long id,
                        Model model
    ) {
        ProductDetails idProductDetails = productDetailsService.detail(id);
        List<ProductDetails> list = productDetailsService.findAllByProductDetail(id);
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
                       @RequestParam("quantity") Integer quantity,
                       @RequestParam("id") Long id,
                       HttpSession session,
                       Model model) {
        LocalDate localDate = LocalDate.now();
        List<ProductDetails> list = productDetailsService.findAllByProductAndColorAndSize(idProduct, idSize, idColor);

        Client client = (Client) session.getAttribute("clientSession");

        for(ProductDetails productDetails : list){
            if(productDetails.getInventory() <= 0 || productDetails.getStatus() == 0){
                model.addAttribute("listProductDetails", productDetailsService.getFindAll());
                model.addAttribute("images", productDetails.getImages());
                model.addAttribute("nameProduct", productDetails.getProduct().getName());
                model.addAttribute("price", productDetails.getPrice());
                model.addAttribute("listSizeShopDetail", productDetailsService.findAllByProductDetail(id));
                model.addAttribute("listProductShopDetail", productDetailsService.findAllByProduct(id));
                model.addAttribute("listColorShopDetail", productDetailsService.findAllByProductDetail(id));
                model.addAttribute("errorInventory", "số lượng sản phẩm trong cửa hàng đã hết ");
                model.addAttribute("viewClient", "/WEB-INF/view/include/shop-detail.jsp");
                return "layout/client";
            }
        }

        if (String.valueOf(client).equalsIgnoreCase("null")) {
            List<DetailedShoppingCart> listShoppingCart = new ArrayList<>();
            ProductDetails details = list.isEmpty() ? null : list.get(0);
            DetailedShoppingCart shoppingCartNull = DetailedShoppingCart.builder()
                    .quantity(quantity)
                    .unitPrice(details.getPrice())
                    .dateCreate(localDate)
                    .status(1)
                    .productDetails(details)
                    .build();
            listShoppingCart.add(shoppingCartNull);
            session.setAttribute("clientNullSession", shoppingCartNull);

            return "redirect:/zephyr/shopping-cart-null";
        }

//        for (ProductDetails productDetails : list) {
//            DetailedShoppingCart shoppingCart = detailedShoppingCartService.findAllByIdShoppingCartAndProductDetails(client.getShoppingCart().getId(), productDetails.getId());
//
//            if(String.valueOf(shoppingCart).equalsIgnoreCase("null")){
//                DetailedShoppingCart detailedShoppingCart = DetailedShoppingCart.builder()
//                        .quantity(quantity)
//                        .unitPrice(productDetails.getPrice())
//                        .dateCreate(localDate)
//                        .status(1)
//                        .shoppingCart(client.getShoppingCart())
//                        .productDetails(productDetails)
//                        .build();
//                detailedShoppingCartService.add(detailedShoppingCart);
//
//                ProductDetails details = productDetailsService.detail(productDetails.getId());
//                productDetails.setInventory(details.getInventory() - quantity);
//                productDetailsService.update(productDetails, productDetails.getId());
//
//                return "redirect:/zephyr/shop";
//            }
//
//            if(shoppingCart.getProductDetails().getId().equals(productDetails.getId())){
//                DetailedShoppingCart detailedShoppingCart = DetailedShoppingCart.builder()
//                        .id(shoppingCart.getId())
//                        .quantity(quantity + shoppingCart.getQuantity())
//                        .unitPrice(productDetails.getPrice())
//                        .dateCreate(localDate)
//                        .status(1)
//                        .shoppingCart(client.getShoppingCart())
//                        .productDetails(productDetails)
//                        .build();
//                detailedShoppingCartService.add(detailedShoppingCart);
//
//                ProductDetails details = productDetailsService.detail(productDetails.getId());
//                productDetails.setInventory(details.getInventory() - quantity);
//                productDetailsService.update(productDetails, productDetails.getId());
//
//                return "redirect:/zephyr/shop";
//            }
//        }

        for(ProductDetails productDetails : list) {
            if (!(quantity <= 10)) {
                model.addAttribute("listProductDetails", productDetailsService.getFindAll());
                model.addAttribute("images", productDetails.getImages());
                model.addAttribute("nameProduct", productDetails.getProduct().getName());
                model.addAttribute("price", productDetails.getPrice());
                model.addAttribute("listSizeShopDetail", productDetailsService.findAllByProductDetail(id));
                model.addAttribute("listProductShopDetail", productDetailsService.findAllByProduct(id));
                model.addAttribute("listColorShopDetail", productDetailsService.findAllByProductDetail(id));
                model.addAttribute("errorInventory", "số lượng mua phải nhỏ hơn hoặc bằng 10 ");
                model.addAttribute("viewClient", "/WEB-INF/view/include/shop-detail.jsp");
                return "layout/client";
            }
        }

        for (ProductDetails productDetails : list) {
            DetailedShoppingCart detailedShoppingCart = DetailedShoppingCart.builder()
                    .quantity(quantity)
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

    @GetMapping("/addToCart")
    public String addToCart(@RequestParam("product") Long idProduct,
                            @RequestParam("size") Long idSize,
                            @RequestParam("color") Long idColor,
                            @RequestParam("quantity") Integer quantity,
                            HttpSession session) {

        LocalDate localDate = LocalDate.now();
        List<ProductDetails> list = productDetailsService.findAllByProductAndColorAndSize(idProduct, idSize, idColor);

        Client client = (Client) session.getAttribute("clientSession");
        if (String.valueOf(client).equalsIgnoreCase("null")) {

            return "redirect:/zephyr/shop/shop-detail?id=";
        }


        return "redirect:/zephyr/shop/shop-detail?id=" + 2;
    }

    @GetMapping("/list")
    public String list(@RequestParam("productData") List<String> productData, Model model) {
        System.out.println("eeeeeeeeeeeeeeeeeeeeeeee                " + productData);


        System.out.println("eeeeeeeeeeeeeeeeeeeeeeee                " + productData);

        return "redirect:/zephyr/shop/shop-detail?id=" + 2;
    }

}

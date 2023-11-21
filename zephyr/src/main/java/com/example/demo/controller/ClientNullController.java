package com.example.demo.controller;

import com.example.demo.entity.ProductDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/zephyr/client-null")
public class ClientNullController {

    @GetMapping("/shopping-cart-null")
    public String shoppingCart(Model model){

        model.addAttribute("viewClient", "/WEB-INF/view/client_null/shopping-cart-null.jsp");
        return "layout/client";
    }

    @GetMapping("/order-null")
    public String orderNull(Model model){


        model.addAttribute("viewClient", "/WEB-INF/view/client_null/shopping-cart-null.jsp");
        return "layout/client";
    }







}

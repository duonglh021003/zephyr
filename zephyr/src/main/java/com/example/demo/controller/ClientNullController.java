package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/zephyr/client-null")
public class ClientNullController {

    @GetMapping("/shopping-cart-null")
    public String shoppingCart(Model model){


        model.addAttribute("viewClient", "/WEB-INF/view/client_null/shopping-cart-null.jsp");
        return "layout/client";
    }

    @GetMapping("/test")
    public String test(){

        return "test/test-01";
    }
}

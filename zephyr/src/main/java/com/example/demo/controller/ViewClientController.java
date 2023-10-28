package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/zephyr")
public class ViewClientController {

    @GetMapping("/shop")
    public String shop(Model model){

        model.addAttribute("viewClient", "/WEB-INF/view/include/shop.jsp");
        return "layout/client";
    }

}

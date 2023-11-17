package com.example.demo.controller;

import com.example.demo.entity.ProductDetails;
import com.example.demo.entity.Staff;
import com.example.demo.service.ProductDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/zephyr/admin/product-detail")
public class ProductDetailController {

    @Autowired
    private ProductDetailsService productDetailsService;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "0", name = "page") Integer number,
                        Model model){
        Pageable pageable = PageRequest.of(number, 6);
        Page<ProductDetails> pageProductDetail = productDetailsService.findAllByStatus(pageable);
        model.addAttribute("listProductDetail", pageProductDetail);
        model.addAttribute("view", "/WEB-INF/view/product_detail/index.jsp");
        return "home/staff";
    }
}

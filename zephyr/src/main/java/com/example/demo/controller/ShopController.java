package com.example.demo.controller;

import com.example.demo.entity.ProductDetails;
import com.example.demo.repository.ColorRepository;
import com.example.demo.repository.OriginRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.SizeRepository;
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

@Controller
@RequestMapping(value = "/zephyr")
public class ShopController {

    @Autowired
    private ProductDetailsService productDetailsService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OriginRepository originRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @GetMapping("/shop")
    public String index(@RequestParam(defaultValue = "0", name = "page") Integer number,
                        Model model
    ) {

        Pageable pageable = PageRequest.of(number, 9);
        Page<ProductDetails> pageProductDetails = productDetailsService.findAllByProductPage(pageable);
        model.addAttribute("listProductDetails", pageProductDetails);

        model.addAttribute("viewClient", "/WEB-INF/view/include/shop.jsp");

        return "layout/client";
    }

    @GetMapping("/shop/search")
    public String search(@RequestParam("minPrice") Double minPrice,
                         @RequestParam("maxPrice") Double maxPrice,
                         @RequestParam("nameColor") String nameColor,
                         @RequestParam("nameSize") String nameSize,
                         @RequestParam(defaultValue = "0", name = "page") Integer number,
                         Model model,
                         HttpSession session) {

        String staffName = (String) session.getAttribute("staffSession.name");
        model.addAttribute("staffSession", staffName);
        Pageable pageable = PageRequest.of(number, 9);
        Page<ProductDetails> pageProductDetails = productDetailsService.getAll(pageable);
        model.addAttribute("listProductDetails", pageProductDetails);
        model.addAttribute("listProductDetails", productDetailsService.findAllByAll(minPrice, maxPrice, nameColor, nameSize));
        model.addAttribute("viewClient", "/WEB-INF/view/include/shop.jsp");

        return "layout/client";
    }

}

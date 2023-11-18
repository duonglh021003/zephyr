package com.example.demo.controller;

import com.example.demo.entity.ProductDetails;
import com.example.demo.entity.Staff;
import com.example.demo.service.ColorService;
import com.example.demo.service.OriginSerivce;
import com.example.demo.service.ProductDetailsService;
import com.example.demo.service.ProductService;
import com.example.demo.service.SizeService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping(value = "/zephyr/admin/product-detail")
public class ProductDetailController {

    @Autowired
    private ProductDetailsService productDetailsService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OriginSerivce originSerivce;

    @Autowired
    private ColorService colorService;

    @Autowired
    private SizeService sizeService;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "0", name = "page") Integer number,
                        Model model) {
        Pageable pageable = PageRequest.of(number, 6);
        Page<ProductDetails> pageProductDetail = productDetailsService.findAllByStatus(pageable);
        model.addAttribute("listProductDetail", pageProductDetail);
        model.addAttribute("view", "/WEB-INF/view/product_detail/index.jsp");
        return "home/staff";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model, HttpSession session) {

        Staff staff = (Staff) session.getAttribute("staffSession");
        if (String.valueOf(staff).equalsIgnoreCase("null")) {
            return "redirect:/zephyr/admin/login";
        }
        LocalDate localDate = LocalDate.now();
        model.addAttribute("dateUpdate", localDate);
        String staffName = (String) session.getAttribute("staffSession.name");
        model.addAttribute("staffSession", staffName);
        model.addAttribute("productDetail", new ProductDetails());

        model.addAttribute("listProduct", productService.getAll());
        model.addAttribute("listOrigin", originSerivce.getAll());
        model.addAttribute("listColor", colorService.getAll());
        model.addAttribute("listSize", sizeService.getAll());
        model.addAttribute("view", "/WEB-INF/view/product_detail/view-add.jsp");
        return "home/staff";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("productDetail") ProductDetails productDetails) {

        productDetailsService.add(productDetails);
        return "redirect:/zephyr/admin/product-detail/index";
    }
}
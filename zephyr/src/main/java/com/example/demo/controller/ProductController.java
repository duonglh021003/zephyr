package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.entity.Staff;
import com.example.demo.message.request.ProductRequest;
import com.example.demo.service.ProductService;
import com.example.demo.validation.Validates;
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
import java.util.Random;

@Controller
@RequestMapping(value = "/zephyr/admin/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "0", name = "page") Integer number,
                        Model model, HttpSession session) {

        Staff staff = (Staff) session.getAttribute("staffSession");
        if (String.valueOf(staff).equalsIgnoreCase("null")) {
            return "redirect:/zephyr/admin/login";
        }

        LocalDate localDate = LocalDate.now();
        Pageable pageable = PageRequest.of(number, 10);
        Page<Product> pageProduct = productService.findAllByStatus1(pageable);
        model.addAttribute("listProduct", pageProduct);
        model.addAttribute("listRestore", productService.findAllByStatus0());
        model.addAttribute("dateUpdate", localDate);
        model.addAttribute("product", new Product());

        model.addAttribute("view", "/WEB-INF/view/product/index.jsp");
        return "home/staff";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model){
        LocalDate localDate = LocalDate.now();
        model.addAttribute("dateUpdate", localDate);
        model.addAttribute("product", new Product());
        model.addAttribute("view", "/WEB-INF/view/product/view-add.jsp");
        return "home/staff";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("product") Product product,
                      BindingResult result,
                      @RequestParam("name") String name,
                      @RequestParam("dateCreate") LocalDate dateCreate,
                      @RequestParam("dateUpdate") LocalDate dateUpdate,
                      @RequestParam("userCreate") String userCreate,
                      @RequestParam("userUpdate") String userUpdate,
                      @RequestParam("status") Integer status,
                      Model model, HttpSession session) {
        String code = productService.getNextCode();

        if (result.hasErrors()) {
            LocalDate localDate = LocalDate.now();
            model.addAttribute("dateUpdate", localDate);
            model.addAttribute("view", "/WEB-INF/view/product/view-add.jsp");
            return "home/staff";
        }
        product = Product.builder()
                .code(code)
                .name(name)
                .dateCreate(dateCreate)
                .dateUpdate(dateUpdate)
                .userCreate(userCreate)
                .userUpdate(userUpdate)
                .status(status)
                .build();
        productService.add(product);

        return "redirect:/zephyr/admin/product/index";
    }

    @GetMapping("/view-update")
    public String viewUpdate(@RequestParam("id") Long id, Model model) {

        LocalDate localDate = LocalDate.now();
        Product product = productService.detail(id);
        model.addAttribute("product", product);
        model.addAttribute("dateUpdate", localDate);

        model.addAttribute("view", "/WEB-INF/view/product/view-update.jsp");
        return "home/staff";
    }

    @PostMapping("update")
    public String update(@Valid @ModelAttribute("product") Product product,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            LocalDate localDate = LocalDate.now();
            model.addAttribute("dateUpdate", localDate);
            model.addAttribute("view", "/WEB-INF/view/product/view-update.jsp");
            return "home/staff";
        }

        productService.update(product, product.getId());
        return "redirect:/zephyr/admin/product/index";
    }

    @GetMapping("/restore")
    public String restore(@RequestParam("id") Long id) {

        Product product = productService.detail(id);
        product.setStatus(1);
        productService.update(product, product.getId());
        return "redirect:/zephyr/admin/product/index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {

        Product product = productService.detail(id);
        product.setStatus(0);
        productService.update(product, product.getId());
        return "redirect:/zephyr/admin/product/index";
    }

    @GetMapping("/search")
    public String search(@RequestParam("inputProduct") String inputProduct,
                         @RequestParam(defaultValue = "0", name = "page") Integer number,
                         Model model){
        Pageable pageable = PageRequest.of(number, 10);
        Page<Product> pageProduct = productService.findAllByProductSearch(inputProduct, pageable);
        model.addAttribute("listProduct", pageProduct);
        model.addAttribute("listRestore", productService.findAllByStatus0());

        model.addAttribute("view", "/WEB-INF/view/product/index.jsp");
        return "home/staff";
    }
}

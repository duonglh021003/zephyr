package com.example.demo.controller;

import com.example.demo.entity.Color;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductDetails;
import com.example.demo.entity.Staff;
import com.example.demo.service.ColorService;
import com.example.demo.service.OriginSerivce;
import com.example.demo.service.ProductDetailsService;
import com.example.demo.service.ProductService;
import com.example.demo.service.SizeService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
                        Model model, HttpSession session) {
        Staff staff = (Staff) session.getAttribute("staffSession");
        if (String.valueOf(staff).equalsIgnoreCase("null")) {
            return "redirect:/zephyr/admin/login";
        }
        LocalDate localDate = LocalDate.now();
        model.addAttribute("dateUpdate", localDate);
        List<ProductDetails> list = productDetailsService.findAllByInventory0();
        for (ProductDetails details : list) {
            details.setStatus(0);
            productDetailsService.update(details, details.getId());
        }

        Pageable pageable = PageRequest.of(number, 10);
        Page<ProductDetails> pageProductDetail = productDetailsService.findAllOrderByIdProductDetail(pageable);
        model.addAttribute("listProductDetail", pageProductDetail);
        model.addAttribute("listProductDetailStatus0", productDetailsService.findAllOrderByIdProductDetailStatus0());
        String staffName = (String) session.getAttribute("staffSession.name");
        model.addAttribute("staffSession", staffName);

        model.addAttribute("view", "/WEB-INF/view/product_detail/index.jsp");
        return "home/staff";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        LocalDate localDate = LocalDate.now();
        model.addAttribute("dateUpdate", localDate);
        model.addAttribute("productDetail", new ProductDetails());

        model.addAttribute("listProduct", productService.getAll());
        model.addAttribute("listOrigin", originSerivce.getAll());
        model.addAttribute("listColor", colorService.getAll());
        model.addAttribute("listSize", sizeService.getAll());
        model.addAttribute("view", "/WEB-INF/view/product_detail/view-add.jsp");
        return "home/staff";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("productDetail") ProductDetails productDetails,
                      BindingResult result,
                      Model model) {

        if (result.hasErrors()) {
            LocalDate localDate = LocalDate.now();
            model.addAttribute("dateUpdate", localDate);

            model.addAttribute("listProduct", productService.getAll());
            model.addAttribute("listOrigin", originSerivce.getAll());
            model.addAttribute("listColor", colorService.getAll());
            model.addAttribute("listSize", sizeService.getAll());
            model.addAttribute("view", "/WEB-INF/view/product_detail/view-add.jsp");
            return "home/staff";
        }

        productDetailsService.add(productDetails);
        return "redirect:/zephyr/admin/product-detail/index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {

        ProductDetails details = productDetailsService.detail(id);
        details.setStatus(0);
        productDetailsService.update(details, details.getId());
        return "redirect:/zephyr/admin/product-detail/index";
    }

    @GetMapping("/restore")
    public String restore(@RequestParam("id") Long id,
                          @RequestParam("inventory") Integer inventory) {
        ProductDetails details = productDetailsService.detail(id);
        details.setInventory(inventory);
        details.setStatus(1);
        productDetailsService.update(details, details.getId());
        return "redirect:/zephyr/admin/product-detail/index";
    }

    @GetMapping("/view-update")
    public String viewupdate(@RequestParam("id") Long id,
                             Model model,
                             HttpSession session) {

        LocalDate localDate = LocalDate.now();
        model.addAttribute("dateUpdate", localDate);
        String staffName = (String) session.getAttribute("staffSession.name");
        model.addAttribute("staffSession", staffName);

        ProductDetails productDetails = productDetailsService.detail(id);
        model.addAttribute("productDetail", productDetails);
        model.addAttribute("listProduct", productService.getAll());
        model.addAttribute("listOrigin", originSerivce.getAll());
        model.addAttribute("listColor", colorService.getAll());
        model.addAttribute("listSize", sizeService.getAll());
        model.addAttribute("view", "/WEB-INF/view/product_detail/view-update.jsp");
        return "home/staff";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("productDetail") ProductDetails productDetails,
                         BindingResult result,
                         @RequestParam("id") Long id,
                         HttpSession session, Model model
    ) {

        if (result.hasErrors()) {
            LocalDate localDate = LocalDate.now();
            model.addAttribute("dateUpdate", localDate);

            model.addAttribute("listProduct", productService.getAll());
            model.addAttribute("listOrigin", originSerivce.getAll());
            model.addAttribute("listColor", colorService.getAll());
            model.addAttribute("listSize", sizeService.getAll());
            model.addAttribute("view", "/WEB-INF/view/product_detail/view-update.jsp");
            return "home/staff";
        }

        String staffName = (String) session.getAttribute("staffSession.name");
        model.addAttribute("staffSession", staffName);
        productDetailsService.update(productDetails, id);
        return "redirect:/zephyr/admin/product-detail/index";
    }

}

package com.example.demo.controller;

import com.example.demo.entity.Origin;
import com.example.demo.entity.Product;
import com.example.demo.entity.Size;
import com.example.demo.service.SizeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping(value = "/zephyr/admin/size")
public class SizeController {

    @Autowired
    private SizeService sizeService;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "0", name = "page") Integer number,
                        Model model){

        Pageable pageable = PageRequest.of(number, 10);
        Page<Size> pageSize = sizeService.findAllByStatus1(pageable);
        model.addAttribute("listSize", pageSize);
        model.addAttribute("listRestore", sizeService.findAllByStatus0());

        model.addAttribute("view", "/WEB-INF/view/size/index.jsp");
        return "home/staff";
    }

//    @PostMapping("/add")
//    public String add(@RequestParam("name") String name,
//                      @RequestParam("dateCreate") LocalDate dateCreate,
//                      @RequestParam("dateUpdate") LocalDate dateUpdate,
//                      @RequestParam("userCreate") String userCreate,
//                      @RequestParam("userUpdate") String userUpdate,
//                      @RequestParam("status") Integer status) {
//
//        Product product = Product.builder()
//                .code(generateRandomString())
//                .name(name)
//                .dateCreate(dateCreate)
//                .dateUpdate(dateUpdate)
//                .userCreate(userCreate)
//                .userUpdate(userUpdate)
//                .status(status)
//                .build();
//        productService.add(product);
//
//        return "redirect:/zephyr/admin/product/index";
//    }
//
//    @GetMapping("/view-update")
//    public String viewUpdate(@RequestParam("id") Long id, Model model) {
//
//        LocalDate localDate = LocalDate.now();
//        Product product = productService.detail(id);
//        model.addAttribute("product", product);
//        model.addAttribute("dateUpdate", localDate);
//
//        model.addAttribute("view", "/WEB-INF/view/product/view-update.jsp");
//        return "home/staff";
//    }

//    @PostMapping("update")
//    public String update(@Valid @ModelAttribute("product") Product product) {
//        productService.update(product, product.getId());
//        return "redirect:/zephyr/admin/product/index";
//    }

    @GetMapping("/restore")
    public String restore(@RequestParam("id") Long id) {

        Size size = sizeService.detail(id);
        size.setStatus(1);
        sizeService.update(size, size.getId());
        return "redirect:/zephyr/admin/size/index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {

        Size size = sizeService.detail(id);
        size.setStatus(0);
        sizeService.update(size, size.getId());
        return "redirect:/zephyr/admin/size/index";
    }

}

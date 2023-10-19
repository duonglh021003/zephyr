package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/zephyr/staff")
public class StaffController {

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("view", "/WEB-INF/view/staff/index.jsp");
        return "home_page/staff";
    }
}

package com.example.demo.controller;

import com.example.demo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/zephyr")
public class HomePage {

    @Autowired
    private StaffService staffService;

    @GetMapping("/staff/home-page")
    public String admin() {
        return "home_page/staff";
    }


}

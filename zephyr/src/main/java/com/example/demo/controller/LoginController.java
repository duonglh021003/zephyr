package com.example.demo.controller;

import com.example.demo.entity.Staff;
import com.example.demo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/zephyr")
public class LoginController {

    @Autowired
    private StaffService staffService;

    @GetMapping("/staff/login")
    public String login() {
        return "login/staff";
    }


    @PostMapping("/staff/home")
    public String signIn(@RequestParam("phoneNumber") String phoneNumber,
                         @RequestParam("password") String password,
                         Model model
    ) {
        Staff staff = staffService.detailPhone(phoneNumber);
        model.addAttribute("staff", staff);
        return staffService.login(phoneNumber, password);
    }


}

package com.example.demo.controller;


import com.example.demo.entity.Staff;
import com.example.demo.service.StaffService;
import jakarta.servlet.http.HttpSession;
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


    @GetMapping("/admin/login")
    public String loginAdmin() {
        return "login/staff";
    }

    @GetMapping("/login")
    public String loginClient() {
        return "login/client";
    }

    @PostMapping("/admin/home")
    public String homeAdmin(@RequestParam("phoneNumberLogin") String phoneNumber,
                            @RequestParam("password") String password,
                            Model model,
                            HttpSession session) {

        Staff staff = staffService.detailPhone(phoneNumber);
        model.addAttribute("staffSession", staff);
        session.setAttribute("staffSession", staff);
        return staffService.login(phoneNumber, password);

    }



}

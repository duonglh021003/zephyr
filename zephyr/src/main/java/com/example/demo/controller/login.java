package com.example.demo.controller;

import com.example.demo.entity.Login;
import com.example.demo.service.Impl.LoginServiceImpl;
import com.example.demo.service.LoginService;
import com.example.demo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes({"phoneNumber", "password"})
@RequestMapping(value = "/zephyr")
public class login {
    private LoginService loginService = new LoginServiceImpl();
    @Autowired
    private StaffService staffService;

    @GetMapping("/staff/login")
    public String login(Model model) {
        model.addAttribute("main", "/WEB-INF/view/login/staff.jsp");
        return "home_page/staff";
    }


    @PostMapping("/staff/sign-in")
    public String signIn(@RequestParam("phoneNumber") String phoneNumber,
                         @RequestParam("password") String password,
                         Model model
    ) {
        Login login = Login.builder().account(phoneNumber).password(password).build();
        loginService.add(login);
        System.out.println("aaaaaaaaaaaaaaaaaaa" + login);
        model.addAttribute("phoneNumber", phoneNumber);
        model.addAttribute("password", password);
        return staffService.login(phoneNumber, password);
    }
}

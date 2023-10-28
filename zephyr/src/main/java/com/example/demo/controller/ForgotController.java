package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/zephyr")
public class ForgotController {

    @GetMapping("/forgot-password")
    public String forget() {

        return "login/forgot";
    }

    
}

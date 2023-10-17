package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/zephyr/admin")
public class admin {

    @GetMapping("/home-page")
    public String home(){
        return "/home_page/admin";
    }

    @GetMapping("/table")
    public String table(){
        return "/login/table";
    }

    @GetMapping("/login")
    public String login(){
        return "/login/staff";
    }



}

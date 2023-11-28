package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/zephyr/admin/voucher-client-detail")
public class DeatilVoucherClientController {

    @GetMapping("/index")
    public String index(Model model){


        model.addAttribute("viewClient", "/WEB-INF/view/voucher_client_detail/voucher.jsp");
        return "layout/client";
    }


}

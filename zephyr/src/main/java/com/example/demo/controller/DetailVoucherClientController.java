package com.example.demo.controller;

import com.example.demo.entity.Client;
import com.example.demo.entity.DetailVoucherClient;
import com.example.demo.entity.VoucherClient;
import com.example.demo.service.DetailVoucherClientService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/zephyr/detail-voucher-client")
public class DetailVoucherClientController {


    @Autowired
    private DetailVoucherClientService detailVoucherClientService;

    @GetMapping()
    public String index(Model model, HttpSession session){

        Client client = (Client) session.getAttribute("clientSession");
        if(String.valueOf(client).equalsIgnoreCase("null")){
            return "redirect:/zephyr/login";
        }
        List<DetailVoucherClient> list = detailVoucherClientService.findAllByIdClient(client.getId());

        model.addAttribute("listDetailVoucherClient", list);
        model.addAttribute("viewClient", "/WEB-INF/view/include/detail-voucher.jsp");
        return "layout/client";
    }

}

package com.example.demo.controller;

import com.example.demo.entity.Invoice;
import com.example.demo.repository.AddressRepository;
import com.example.demo.service.AddressService;
import com.example.demo.service.DetailedInvoiceService;
import com.example.demo.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/zephyr/admin/invoice-detail")
public class DetailInvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private DetailedInvoiceService detailedInvoiceService;

    @Autowired
    private AddressService addressService;

    @GetMapping("/detail")
    public String detail(@RequestParam("id") Long id,
                         Model model){

        Invoice invoice = invoiceService.detail(id);



        model.addAttribute("listDetailInvoice", detailedInvoiceService.findAllByIdInvoice(id));
        model.addAttribute("listAddress", addressService.findAllAddress(id));
        model.addAttribute("listInvoice", invoiceService.findAllByInvoice(id));
        model.addAttribute("codeInvoice", invoice.getCode());
        model.addAttribute("view", "/WEB-INF/view/detail_invoice/index.jsp");
        return "home/staff";
    }

    @GetMapping("/wait-for-confirmation")
    public String Status2(@RequestParam("id") Long id,
                         Model model){

        Invoice invoice = invoiceService.detail(id);

        model.addAttribute("listDetailInvoice", detailedInvoiceService.findAllByIdInvoice(id));
        model.addAttribute("listAddress", addressService.findAllAddress(id));
        model.addAttribute("listInvoice", invoiceService.findAllByInvoice(id));
        model.addAttribute("codeInvoice", invoice.getCode());
        model.addAttribute("view", "/WEB-INF/view/detail_invoice/index-status-2.jsp");
        return "home/staff";
    }

}

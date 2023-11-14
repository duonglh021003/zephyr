package com.example.demo.controller;

import com.example.demo.entity.Staff;
import com.example.demo.service.InvoiceService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/zephyr/admin/delivery-notes")
public class DeliveryNotesController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        Staff staff = (Staff) session.getAttribute("staffSession");
        if (String.valueOf(staff).equalsIgnoreCase("null")) {
            return "redirect:/zephyr/admin/login";
        }
        model.addAttribute("listInvoiceStaff", invoiceService.findAllByIdStaff(staff.getId()));
        model.addAttribute("view", "/WEB-INF/view/delivery_notes/index.jsp");
        return "home/staff";
    }
}

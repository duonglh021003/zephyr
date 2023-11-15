package com.example.demo.controller;

import com.example.demo.entity.Invoice;
import com.example.demo.entity.Staff;
import com.example.demo.service.InvoiceService;
import com.example.demo.service.ProductDetailsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping(value = "/zephyr/admin/sell")
public class SellController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ProductDetailsService productDetailsService;


    private static final Random random = new Random();

    public static String generateRandomString() {
        StringBuilder sb = new StringBuilder(10);
        sb.append("ZN");
        for (int i = 0; i < 5; i++) {
            int rndNum = random.nextInt(10);
            sb.append(rndNum);
        }
        return sb.toString();
    }

    @GetMapping("/index")
    public String index(Model model){

        model.addAttribute("listInvoiceStatus0", invoiceService.findAllByStatus0());
        model.addAttribute("view", "/WEB-INF/view/offline_sell/index.jsp");
        return "home/staff";
    }

    @GetMapping("/add")
    public String add(Model model, HttpSession session){

        Staff staff = (Staff) session.getAttribute("staffSession");
        if (String.valueOf(staff).equalsIgnoreCase("null")) {
            return "redirect:/zephyr/admin/login";
        }
        LocalDate localDate = LocalDate.now();
        ZoneId zoneId = ZoneId.of("Asia/Ho_Chi_Minh");
        LocalTime currentTime = LocalTime.now(zoneId);
        List<Invoice> list = invoiceService.findAllByStatus0();

        int count = list.size();
        for(int i = 0; count < 5; i++){
            Invoice invoice = Invoice.builder()
                    .code(generateRandomString())
                    .hourMinute(currentTime.getHour() + ":" + currentTime.getMinute())
                    .dateCreate(localDate)
                    .status(0)
                    .staff(staff)
                    .build();
            invoiceService.add(invoice);
            count++;
            return "redirect:/zephyr/admin/sell/index";
        }
        return "redirect:/zephyr/admin/sell/index";
    }

    @GetMapping("/invoice")
    public String invoiceDetail(@RequestParam("id") Long id,
                                Model model){
        Invoice invoice = invoiceService.detail(id);

        model.addAttribute("listInvoiceStatus0", invoiceService.findAllByStatus0());
        model.addAttribute("listDetailProduct", productDetailsService.findAllByDisplaySell());
        model.addAttribute("codeInvoice", invoice.getCode());
        model.addAttribute("view", "/WEB-INF/view/offline_sell/invoice.jsp");
        return "home/staff";
    }
}

package com.example.demo.controller;

import com.example.demo.entity.Client;
import com.example.demo.entity.DetailDeliveryNotes;
import com.example.demo.entity.DetailedInvoice;
import com.example.demo.entity.Invoice;
import com.example.demo.service.DetailDeliveryNotesService;
import com.example.demo.service.DetailedInvoiceService;
import com.example.demo.service.InvoiceService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/zephyr/purchase-order")
public class PurchaseOrderController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private DetailDeliveryNotesService detailDeliveryNotesService;

    @Autowired
    private DetailedInvoiceService detailedInvoiceService;

    @GetMapping()
    public String index(Model model, HttpSession session){

        Client client = (Client) session.getAttribute("clientSession");
        if (String.valueOf(client).equalsIgnoreCase("null")) {
            return "redirect:/zephyr/login";
        }

        model.addAttribute("listInvoice", invoiceService.findByInvoiceStatusAll(client.getId()));
        model.addAttribute("listInvoiceStatus02", invoiceService.findByInvoiceStatus2(client.getId()));
        model.addAttribute("listInvoiceStatus03", invoiceService.findByInvoiceStatus3(client.getId()));
        model.addAttribute("listInvoiceStatus04", invoiceService.findByInvoiceStatus4(client.getId()));
        model.addAttribute("listInvoiceStatus05", invoiceService.findByInvoiceStatus5(client.getId()));
        model.addAttribute("listInvoiceStatus07", invoiceService.findByInvoiceStatus7(client.getId()));

        model.addAttribute("viewClient", "/WEB-INF/view/include/purchase-order.jsp");
        return "layout/client";
    }

    @GetMapping("/update-status2")
    public String updateStatus2(@RequestParam("id") Long id){
        Invoice invoice = invoiceService.detail(id);
        invoice.setStatus(7);
        invoiceService.update(invoice, invoice.getId());

        return "redirect:/zephyr/purchase-order";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("id") Long id,
                         Model model){
        Invoice invoice = invoiceService.detail(id);
        List<DetailDeliveryNotes> list = detailDeliveryNotesService.findAllDetailDeliveryNotesMax(id);

        for (DetailDeliveryNotes notes : list) {
            model.addAttribute("images", notes.getProgress());
        }
        model.addAttribute("listDate", detailDeliveryNotesService.findAllByIdInvoice(id));
        model.addAttribute("codeInvoice", invoice.getCode());
        model.addAttribute("statusInvoice", invoice.getByStatus());
        model.addAttribute("listDetailInvoice", detailedInvoiceService.findAllByIdInvoice(id));
        model.addAttribute("listInvoice", invoiceService.findAllByInvoice(id));


        model.addAttribute("viewClient", "/WEB-INF/view/include/detail-purchase-order.jsp");
        return "layout/client";
    }
}

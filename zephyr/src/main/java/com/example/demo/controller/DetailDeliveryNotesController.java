package com.example.demo.controller;

import com.example.demo.entity.Address;
import com.example.demo.entity.Client;
import com.example.demo.entity.DeliveryNotes;
import com.example.demo.entity.DetailDeliveryNotes;
import com.example.demo.entity.DetailedInvoice;
import com.example.demo.entity.Invoice;
import com.example.demo.entity.ProductDetails;
import com.example.demo.service.AddressService;
import com.example.demo.service.ClientService;
import com.example.demo.service.DeliveryNotesService;
import com.example.demo.service.DetailDeliveryNotesService;
import com.example.demo.service.DetailedInvoiceService;
import com.example.demo.service.InvoiceService;
import com.example.demo.service.ProductDetailsService;
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

@Controller
@RequestMapping(value = "/zephyr/admin/detail-delivery-notes")
public class DetailDeliveryNotesController {

    @Autowired
    private DetailDeliveryNotesService detailDeliveryNotesService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private DetailedInvoiceService detailedInvoiceService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private DeliveryNotesService deliveryNotesService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductDetailsService productDetailsService;

    @GetMapping("/detail")
    public String detail(@RequestParam("id") Long id,
                         Model model) {
        Invoice invoice = invoiceService.detail(id);
        List<DetailDeliveryNotes> list = detailDeliveryNotesService.findAllDetailDeliveryNotesMax(id);

        for (DetailDeliveryNotes notes : list) {
            model.addAttribute("images", notes.getProgress());
        }

        model.addAttribute("listDetailDeliveryNote", list);
        model.addAttribute("listDate", detailDeliveryNotesService.findAllByIdInvoice(id));
        model.addAttribute("listDetailInvoice", detailedInvoiceService.findAllByIdInvoice(id));
        model.addAttribute("listAddress", addressService.findAllAddress(id));
        model.addAttribute("listInvoice", invoiceService.findAllByInvoice(id));
        model.addAttribute("codeInvoice", invoice.getCode());
        model.addAttribute("view", "/WEB-INF/view/detail_delivery_notes/detail.jsp");
        return "home/staff";
    }

    @GetMapping("/add")
    public String add(@RequestParam("id") Long id) {

        LocalDate localDate = LocalDate.now();
        ZoneId zoneId = ZoneId.of("Asia/Ho_Chi_Minh");
        LocalTime currentTime = LocalTime.now(zoneId);


        DetailDeliveryNotes deliveryNotes = detailDeliveryNotesService.detail(id);

        String name = "";
        String getprogresss = "";
        Integer status = 0;
        if (deliveryNotes.getStatus() == 3) {
            name = "Đã Giao Cho ĐVVC ";
            getprogresss = "4.png";
            status = 4;
        } else if (deliveryNotes.getStatus() == 4) {
            name = "Đã Nhận Được Hàng ";
            getprogresss = "5.png";
            status = 5;
        }else if (deliveryNotes.getStatus() == 5) {
            name = "Đơn Hàng Hoàn Thành ";
            getprogresss = "6.png";
            status = 6;
        }
        Invoice invoice = invoiceService.detail(deliveryNotes.getInvoice().getId());

//        if(invoice.getStatus() == 3){
//            for (DetailedInvoice detailedInvoice : detailedInvoiceService.findAllByIdInvoice(invoice.getId())) {
//                ProductDetails productDetails = productDetailsService.detail(detailedInvoice.getProductDetails().getId());
//                productDetails.setInventory(productDetails.getInventory() - detailedInvoice.getQuantity());
//                productDetailsService.update(productDetails, productDetails.getId());
//            }
//        }

        invoice.setStatus(status);
        DeliveryNotes notes = deliveryNotesService.detail(deliveryNotes.getDeliveryNotes().getId());
        notes.setStatus(status);
        DetailDeliveryNotes detailDeliveryNotes = DetailDeliveryNotes.builder()
                .detailNotesName(name)
                .progress(getprogresss)
                .hourMinute(currentTime.getHour() + ":" + currentTime.getMinute())
                .dateCreate(localDate)
                .note("không")
                .status(status)
                .invoice(deliveryNotes.getInvoice())
                .deliveryNotes(deliveryNotes.getDeliveryNotes())
                .build();
        detailDeliveryNotesService.add(detailDeliveryNotes);
        Long idInvoice = deliveryNotes.getInvoice().getId();
        return "redirect:/zephyr/admin/detail-delivery-notes/detail?id=" + idInvoice;

    }
}

package com.example.demo.controller;

import com.example.demo.entity.Client;
import com.example.demo.entity.DeliveryNotes;
import com.example.demo.entity.DetailDeliveryNotes;
import com.example.demo.entity.Invoice;
import com.example.demo.entity.Staff;
import com.example.demo.service.DeliveryNotesService;
import com.example.demo.service.DetailDeliveryNotesService;
import com.example.demo.service.InvoiceService;
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

@Controller
@RequestMapping(value = "/zephyr/admin/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private DeliveryNotesService deliveryNotesService;

    @Autowired
    private DetailDeliveryNotesService detailDeliveryNotesService;

    @GetMapping("/wait-for-confirmation")
    public String status2(Model model, HttpSession session) {

        model.addAttribute("listInvoiceStatus02", invoiceService.findAllByStatus2());
        model.addAttribute("view", "/WEB-INF/view/invoice/status-02.jsp");
        return "home/staff";
    }

    @GetMapping("/update-status-2")
    public String updateStatus2(@RequestParam("id") Long id,
                                Model model, HttpSession session) {

        Staff staff = (Staff) session.getAttribute("staffSession");
        if (String.valueOf(staff).equalsIgnoreCase("null")) {
            return "redirect:/zephyr/admin/login";
        }
        LocalDate localDate = LocalDate.now();
        ZoneId zoneId = ZoneId.of("Asia/Ho_Chi_Minh");
        LocalTime currentTime = LocalTime.now(zoneId);


        Invoice invoice = invoiceService.detail(id);
        invoice.setStatus(3);
        invoice.setStaff(staff);
//        invoiceService.update(invoice, id);

        String address = invoice.getAddress().getClientAddress() + ", " +
                invoice.getAddress().getCommune() + ", " +
                invoice.getAddress().getDistrict() + ", " +
                invoice.getAddress().getCity();

        LocalDate dateCreate = invoice.getDateCreate();
        LocalDate getDateDelivers = dateCreate.plusDays(1);
        LocalDate getDateReceives = getDateDelivers.plusDays(3);
        DeliveryNotes deliveryNotes = DeliveryNotes
                .builder()
                .clientName(invoice.getAddress().getName())
                .phoneNumber(invoice.getAddress().getPhoneNumber())
                .addressClient(address)
                .dateOrder(invoice.getDateCreate())
                .dateDeliver(getDateDelivers)
                .dateReceive(getDateReceives)
                .note("không")
                .status(3)
                .build();
        deliveryNotesService.add(deliveryNotes);

        System.out.println("aaaaaaaaaaaaaaaaaaaaaaa         "+deliveryNotes);

        DetailDeliveryNotes detailDeliveryNotesStatus2 = DetailDeliveryNotes.builder()
                .detailNotesName("Đơn Hàng Đã Đặt")
                .progress("6.png")
                .hourMinute(invoice.getHourMinute())
                .dateCreate(invoice.getDateCreate())
                .note("không")
                .status(2)
                .invoice(invoice)
                .deliveryNotes(deliveryNotes)
                .build();

        detailDeliveryNotesService.add(detailDeliveryNotesStatus2);

        DetailDeliveryNotes detailDeliveryNotesStatus3 = DetailDeliveryNotes.builder()
                .detailNotesName("Đã Xác Nhận Đơn Hàng")
                .progress("6.png")
                .hourMinute(currentTime.getHour() + ":" + currentTime.getMinute())
                .dateCreate(localDate)
                .note("không")
                .status(3)
                .invoice(invoice)
                .deliveryNotes(deliveryNotes)
                .build();
        detailDeliveryNotesService.add(detailDeliveryNotesStatus3);
        return "redirect:/zephyr/admin/invoice/wait-for-confirmation";
    }
}

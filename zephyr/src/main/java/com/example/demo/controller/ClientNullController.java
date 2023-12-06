package com.example.demo.controller;

import com.example.demo.entity.Address;
import com.example.demo.entity.DetailedInvoice;
import com.example.demo.entity.DetailedShoppingCart;
import com.example.demo.entity.Invoice;
import com.example.demo.entity.Payment;
import com.example.demo.service.AddressService;
import com.example.demo.service.DetailedInvoiceService;
import com.example.demo.service.InvoiceService;
import com.example.demo.service.PaymentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

@Controller
@RequestMapping(value = "/zephyr")
public class ClientNullController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private DetailedInvoiceService detailedInvoiceService;

    @Autowired
    private PaymentService paymentService;

    private String incrementCodeOrder(String codeOrder) {
        String prefix = codeOrder.substring(0, 2);
        int number = Integer.parseInt(codeOrder.substring(2));
        number++;
        String nextCodeOrder = String.format("%s%05d", prefix, number);
        return nextCodeOrder;
    }

    private String getNextCode() {
        String currentCodeOrder = invoiceService.findMaxCodeOrder();
        String nextCodeOrder = incrementCodeOrder(currentCodeOrder);
        return nextCodeOrder;
    }

    private String getNextCodeAddress() {
        String currentCodeAddress = addressService.findMaxCodeAddress();
        String nextCodeAddress = incrementCodeOrder(currentCodeAddress);
        return nextCodeAddress;
    }

    @GetMapping("/shopping-cart-null")
    public String shoppingCart(Model model) {

        model.addAttribute("viewClient", "/WEB-INF/view/client_null/shopping-cart-null.jsp");
        return "layout/client";
    }

    @GetMapping("/order-null/add")
    public String orderNullAdd(Model model, HttpSession session) {
        LocalDate localDate = LocalDate.now();
        ZoneId zoneId = ZoneId.of("Asia/Ho_Chi_Minh");
        LocalTime currentTime = LocalTime.now(zoneId);

        DetailedShoppingCart shoppingCartNull = (DetailedShoppingCart) session.getAttribute("clientNullSession");

        Double getCapitalSum = shoppingCartNull.getQuantity() * shoppingCartNull.getUnitPrice();
        Double intoMoney = getCapitalSum - 15.0;

        Invoice invoice = Invoice.builder()
                .code(getNextCode())
                .hourMinute(currentTime.getHour() + ":" + currentTime.getMinute())
                .dateCreate(localDate)
                .totalInvoice(getCapitalSum)
                .shippingMoney(15.0)
                .intoMoney(intoMoney)
                .status(2)
                .build();
        invoiceService.add(invoice);
        return "redirect:/zephyr/order-null";
    }

    @GetMapping("/order-null")
    public String order(Model model, HttpSession session) {

        List<Invoice> listInvoice = invoiceService.findAllInvoiceClientNull();
        model.addAttribute("listInvoice", listInvoice);

        model.addAttribute("viewClient", "/WEB-INF/view/client_null/order-null.jsp");
        return "layout/client";

    }

    @PostMapping("/order-null/update")
    public String orderNullUpdate(@RequestParam("code") String code,
                                  @RequestParam("hourMinute") String hourMinute,
                                  @RequestParam("dateCreate") LocalDate dateCreate,
                                  @RequestParam("totalInvoice") Double totalInvoice,
                                  @RequestParam("point") Double point,
                                  @RequestParam("shippingMoney") Double shippingMoney,
                                  @RequestParam("intoMoney") Double intoMoney,
                                  @RequestParam("note") String note,
                                  @RequestParam("payment") Long payment,
                                  Model model, HttpSession session) {

        Invoice invoice = invoiceService.detailCode(code);
        List<Address> list = addressService.findAllClientNull();
        Address address = list.isEmpty() ? null : list.get(0);
        Payment payment01 = paymentService.detail(payment);
        DetailedShoppingCart detailedShoppingCart = (DetailedShoppingCart)  session.getAttribute("clientNullSession");


        invoice = Invoice.builder()
                .id(invoice.getId())
                .code(code)
                .hourMinute(hourMinute)
                .dateCreate(dateCreate)
                .totalInvoice(totalInvoice)
                .shippingMoney(shippingMoney)
                .intoMoney(intoMoney)
                .note(note)
                .status(2)
                .address(address)
                .payment(payment01)
                .build();
        invoiceService.update(invoice, invoice.getId());

        Double getCapitalSum = detailedShoppingCart.getQuantity() * detailedShoppingCart.getUnitPrice();
        DetailedInvoice detailedInvoice = DetailedInvoice.builder()
                .quantity(detailedShoppingCart.getQuantity())
                .unitPrice(detailedShoppingCart.getUnitPrice())
                .capitalSum(getCapitalSum)
                .status(detailedShoppingCart.getStatus())
                .invoice(invoice)
                .productDetails(detailedShoppingCart.getProductDetails())
                .build();
        detailedInvoiceService.add(detailedInvoice);
        session.removeAttribute("clientNullSession");
        return "redirect:/zephyr/shop";
    }

    @PostMapping("/order-null/address-null/add")
    public String AddressNullAdd(@RequestParam("name") String name,
                                 @RequestParam("phoneNumber") String phoneNumber,
                                 @RequestParam("city") String city,
                                 @RequestParam("district") String district,
                                 @RequestParam("commune") String commune,
                                 @RequestParam("clientAddress") String clientAddress,
                                 Model model){
        LocalDate localDate = LocalDate.now();

        Address address = Address.builder()
                .code(getNextCodeAddress())
                .name(name)
                .phoneNumber(phoneNumber)
                .city(city)
                .district(district)
                .commune(commune)
                .clientAddress(clientAddress)
                .dateCreate(localDate)
                .status(1)
                .build();
        addressService.add(address);

        List<Invoice> listInvoice = invoiceService.findAllInvoiceClientNull();
        model.addAttribute("listInvoice", listInvoice);
        model.addAttribute("listAddress", addressService.findAllClientNull());
        model.addAttribute("viewClient", "/WEB-INF/view/client_null/order-null.jsp");
        return "layout/client";

    }


}

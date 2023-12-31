package com.example.demo.controller;

import com.example.demo.entity.Address;
import com.example.demo.entity.Client;
import com.example.demo.entity.DetailVoucherClient;
import com.example.demo.entity.DetailedInvoice;
import com.example.demo.entity.DetailedShoppingCart;
import com.example.demo.entity.Invoice;
import com.example.demo.entity.Payment;
import com.example.demo.entity.ProductDetails;
import com.example.demo.entity.ShoppingCart;
import com.example.demo.entity.Voucher;
import com.example.demo.entity.VoucherClient;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.service.AddressService;
import com.example.demo.service.ClientService;
import com.example.demo.service.DetailVoucherClientService;
import com.example.demo.service.DetailedInvoiceService;
import com.example.demo.service.DetailedShoppingCartService;
import com.example.demo.service.InvoiceService;
import com.example.demo.service.ProductDetailsService;
import com.example.demo.service.ShoppingCartService;
import com.example.demo.service.VoucherClientService;
import com.example.demo.service.VoucherService;
import jakarta.servlet.http.HttpSession;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping(value = "/zephyr/shop")
public class OrderController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private DetailedShoppingCartService detailedShoppingCartService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private DetailedInvoiceService detailedInvoiceService;

    @Autowired
    private PaymentRepository paymentRepository;


    @Autowired
    private VoucherClientService voucherClientService;

    @Autowired
    private ProductDetailsService productDetailsService;

    @Autowired
    private DetailVoucherClientService detailVoucherClientService;



    private String incrementCodeOrder(String code) {
        String prefix = code.substring(0, 2);
        int number = Integer.parseInt(code.substring(2));
        number++;
        String nextCode= String.format("%s%05d", prefix, number);
        return nextCode;
    }

    private String getNextCodeInvoice() {
        String currentCodeOrder = invoiceService.findMaxCodeOrder();
        String nextCodeOrder = incrementCodeOrder(currentCodeOrder);
        return nextCodeOrder;
    }

    private String getNextCodeAddress() {
        String currentCodeAddress = addressService.findMaxCodeAddress();
        String nextCodeAddress = incrementCodeOrder(currentCodeAddress);
        return nextCodeAddress;
    }
    
    @GetMapping("/order/add")
    public String orderAdd(Model model, HttpSession session) {
        LocalDate localDate = LocalDate.now();
        Long idShopping;
        Double intoMoney = 0.00;
        Double total = 0.00;
        ZoneId zoneId = ZoneId.of("Asia/Ho_Chi_Minh");
        LocalTime currentTime = LocalTime.now(zoneId);
        Client client = (Client) session.getAttribute("clientSession");
        if (String.valueOf(client).equalsIgnoreCase("null")) {
            return "redirect:/zephyr/login";
        }
        idShopping = client.getShoppingCart().getId();
        List<DetailedShoppingCart> list = detailedShoppingCartService.findAllById(idShopping);

        for (DetailedShoppingCart cart : list) {
            total += cart.subTotal();
            ShoppingCart shoppingCart = ShoppingCart.builder()
                    .id(idShopping)
                    .code(cart.getShoppingCart().getCode())
                    .totalShoppingCart(total)
                    .status(1)
                    .build();
            shoppingCartService.update(shoppingCart, idShopping);
        }

        intoMoney = total + 15.00;
        List<Address> addresses = clientService.findAllByIdAndStatus(client.getId());
        Address address = addresses.isEmpty() ? null : addresses.get(0);


        Invoice invoice = Invoice.builder()
                .code(getNextCodeInvoice())
                .hourMinute(currentTime.getHour() + ":" + currentTime.getMinute())
                .dateCreate(localDate)
                .totalInvoice(total)
                .point(0.00)
                .shippingMoney(15.00)
                .intoMoney(intoMoney)
                .status(1)
                .client(client)
                .address(address)
                .build();

        invoiceService.add(invoice);

        return "redirect:/zephyr/shop/order";
    }

    @GetMapping("/order")
    public String order(Model model, HttpSession session) {

        Client client = (Client) session.getAttribute("clientSession");
        if (String.valueOf(client).equalsIgnoreCase("null")) {
            return "redirect:/zephyr/login";
        }
        List<Invoice> listInvoice = invoiceService.findByInvoiceMax(client.getId());
        List<DetailedShoppingCart> listDetailShopingCart = detailedShoppingCartService.findAllById(client.getShoppingCart().getId());
        model.addAttribute("listAddresOrder", clientService.findAllById(client.getId()));
        model.addAttribute("listAddress", clientService.findAllByIdAndStatus(client.getId()));
        model.addAttribute("listInvoice", invoiceService.findByInvoiceMax(client.getId()));
        model.addAttribute("listDetailShoppingCart", listDetailShopingCart);

        model.addAttribute("viewClient", "/WEB-INF/view/include/order.jsp");

        return "layout/client";
    }

    @PostMapping("/order/update")
    public String update(@RequestParam("code") String code,
                         @RequestParam("hourMinute") String hourMinute,
                         @RequestParam("dateCreate") LocalDate dateCreate,
                         @RequestParam("totalInvoice") Double totalInvoice,
                         @RequestParam("point") Double point,
                         @RequestParam("shippingMoney") Double shippingMoney,
                         @RequestParam("intoMoney") Double intoMoney,
                         @RequestParam("note") String note,
                         @RequestParam("payment") Long payment,
                         Model model, HttpSession session) {

        Payment payment1 = Payment.builder().id(payment).build();

        Client client = (Client) session.getAttribute("clientSession");
        if (String.valueOf(client).equalsIgnoreCase("null")) {
            return "redirect:/zephyr/login";
        }

        List<Address> addresses = clientService.findAllByIdAndStatus(client.getId());
        Address address = addresses.isEmpty() ? null : addresses.get(0);
        Invoice invoice01 = invoiceService.detailCode(code);
        Invoice invoice = Invoice.builder()
                .id(invoice01.getId())
                .code(code)
                .hourMinute(hourMinute)
                .dateCreate(dateCreate)
                .totalInvoice(totalInvoice)
                .point(point)
                .shippingMoney(shippingMoney)
                .intoMoney(intoMoney)
                .note(note)
                .status(2)
                .client(client)
                .address(address)
                .payment(payment1)
                .detailVoucherClient(invoice01.getDetailVoucherClient())
                .build();

        Double getCapitalSum = 0.00;
        for (DetailedShoppingCart cart : detailedShoppingCartService.findAllShoppingDetail(client.getId())) {
            getCapitalSum = cart.getQuantity() * cart.getUnitPrice();
            DetailedInvoice detailedInvoice = DetailedInvoice.builder()
                    .quantity(cart.getQuantity())
                    .unitPrice(cart.getUnitPrice())
                    .capitalSum(getCapitalSum)
                    .status(1)
                    .invoice(invoice01)
                    .productDetails(cart.getProductDetails())
                    .build();
            detailedInvoiceService.add(detailedInvoice);
            detailedShoppingCartService.delete(cart.getId());
        }

        for (Invoice invoice02 : invoiceService.findByInvoiceStatus1(client.getId())) {
            if (String.valueOf(invoice02.getDetailVoucherClient()).equalsIgnoreCase("null") == false) {
                DetailVoucherClient detailVoucherClient = detailVoucherClientService.detail(invoice.getDetailVoucherClient().getId());
                detailVoucherClient.setQuantity(0);
                detailVoucherClient.setStatus(0);
                detailVoucherClientService.update(detailVoucherClient, detailVoucherClient.getId());
            }
        }

        if (String.valueOf(invoice.getClient()).equalsIgnoreCase("null") == false) {
            Double getPoints = invoice.getIntoMoney() / 100 + invoice.getIntoMoney() * (client.getRank().getPercent() / 100);
            Double getPointUsrs = getPoints + (client.getPointUsr() - invoice.getPoint());

            Double accumulatedScore = invoice.getIntoMoney() / 100;
            Double getAccumulatedScores = accumulatedScore + client.getAccumulatedScore();
            client.setPointUsr(getPointUsrs);
            client.setAccumulatedScore(getAccumulatedScores);
            clientService.update(client, client.getId());
        }

        invoiceService.update(invoice, invoice01.getId());
        return "redirect:/zephyr/shop";
    }

    @PostMapping("/order-address/add")
    public String orderAddressAdd(@RequestParam("name") String name,
                                  @RequestParam("phoneNumber") String phoneNumber,
                                  @RequestParam("city") String city,
                                  @RequestParam("district") String district,
                                  @RequestParam("commune") String commune,
                                  @RequestParam("clientAddress") String clientAddress,
                                  @RequestParam("status") Integer status,
                                  HttpSession session) {

        LocalDate localDate = LocalDate.now();
        Client client = (Client) session.getAttribute("clientSession");
        List<Address> list = clientService.findAllById(client.getId());
        for (Address p : list) {
            if (status.equals(1)) {
                if (p.getStatus().equals(1)) {
                    p.setStatus(0);
                }
            }
        }
        Address address = Address.builder()
                .code(getNextCodeAddress())
                .name(name)
                .phoneNumber(phoneNumber)
                .city(city)
                .district(district)
                .commune(commune)
                .clientAddress(clientAddress)
                .dateCreate(localDate)
                .status(status)
                .client(client)
                .build();
        addressService.add(address);
        return "redirect:/zephyr/shop/order";
    }

    @PostMapping("/order/voucher/add")
    public String addVoucher(@RequestParam("voucher") String codeVoucher,
                             Model model, HttpSession session) {

        Double intoMoney = 0.00;
        Double point = 0.00;
        Client client = (Client) session.getAttribute("clientSession");
        if (String.valueOf(client).equalsIgnoreCase("null")) {
            return "redirect:/zephyr/login";
        }

        DetailVoucherClient detailVoucherClient = detailVoucherClientService.detailCode(codeVoucher);
        List<Invoice> invoice = invoiceService.findByInvoiceMax(client.getId());
        for (Invoice invoice1 : invoice) {
            if (String.valueOf(invoice1.getPoint()).equalsIgnoreCase("0.0")) {
                point = 0.00;
            } else {
                point = invoice1.getPoint();
            }
            intoMoney = invoice1.getTotalInvoice() - detailVoucherClient.getReducedPrice() + invoice1.getShippingMoney() - point;
            invoice1.setIntoMoney(intoMoney);
            invoice1.setDetailVoucherClient(detailVoucherClient);
            invoiceService.update(invoice1, invoice1.getId());
        }

        return "redirect:/zephyr/shop/order";
    }

    @PostMapping("/order/point-usr/add")
    public String addPointUsr(@RequestParam("point") Double point,
                              Model model, HttpSession session) {

        Double intoMoney = 0.00;
        Double voucher = 0.00;
        Client client = (Client) session.getAttribute("clientSession");
        if (String.valueOf(client).equalsIgnoreCase("null")) {
            return "redirect:/zephyr/login";
        }


        List<Invoice> invoice = invoiceService.findByInvoiceMax(client.getId());
        for (Invoice invoice1 : invoice) {
            if (String.valueOf(invoice1.getDetailVoucherClient()).equalsIgnoreCase("null")) {
                voucher = 0.00;
            } else {
                voucher = invoice1.getDetailVoucherClient().getReducedPrice();
            }
            intoMoney = invoice1.getTotalInvoice() - voucher + invoice1.getShippingMoney() - point;
            invoice1.setPoint(point);
            invoice1.setIntoMoney(intoMoney);
            invoiceService.update(invoice1, invoice1.getId());

        }
        return "redirect:/zephyr/shop/order";
    }



}

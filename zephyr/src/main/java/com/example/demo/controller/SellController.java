package com.example.demo.controller;

import com.example.demo.entity.Address;
import com.example.demo.entity.Client;
import com.example.demo.entity.DetailedInvoice;
import com.example.demo.entity.DetailedShoppingCart;
import com.example.demo.entity.Invoice;
import com.example.demo.entity.ProductDetails;
import com.example.demo.entity.Staff;
import com.example.demo.entity.Voucher;
import com.example.demo.service.ClientService;
import com.example.demo.service.ColorService;
import com.example.demo.service.DetailedInvoiceService;
import com.example.demo.service.InvoiceService;
import com.example.demo.service.ProductDetailsService;
import com.example.demo.service.SizeService;
import com.example.demo.service.VoucherService;
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
import java.util.Random;

@Controller
@RequestMapping(value = "/zephyr/admin/sell")
public class SellController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ProductDetailsService productDetailsService;

    @Autowired
    private DetailedInvoiceService detailedInvoiceService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private VoucherService voucherService;

    @Autowired
    private SizeService sizeService;

    @Autowired
    private ColorService colorService;


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
    public String index(Model model) {

        List<Invoice> list = invoiceService.findAllByStatus0();

        int count = list.size();
        System.out.println("aaaaaaaaaaaaaaaaaa          " + count);
        if (count == 5) {
            System.out.println("bbbbbbbbbbbbbbbbbbbbbbb");
            model.addAttribute("errorMessage", "bạn chỉ có thể tạo đối đa 5 hoá đơn!");
        }
        model.addAttribute("listInvoiceStatus0", invoiceService.findAllByStatus0());
        model.addAttribute("view", "/WEB-INF/view/offline_sell/index.jsp");
        return "home/staff";
    }

    @GetMapping("/add")
    public String add(Model model, HttpSession session) {

        Staff staff = (Staff) session.getAttribute("staffSession");
        if (String.valueOf(staff).equalsIgnoreCase("null")) {
            return "redirect:/zephyr/admin/login";
        }
        LocalDate localDate = LocalDate.now();
        ZoneId zoneId = ZoneId.of("Asia/Ho_Chi_Minh");
        LocalTime currentTime = LocalTime.now(zoneId);
        List<Invoice> list = invoiceService.findAllByStatus0();

        int count = list.size();
        for (int i = 0; count < 5; i++) {
            Invoice invoice = Invoice.builder()
                    .code(generateRandomString())
                    .hourMinute(currentTime.getHour() + ":" + currentTime.getMinute())
                    .dateCreate(localDate)
                    .status(0)
                    .staff(staff)
                    .build();
            invoiceService.add(invoice);
            count++;
            return "redirect:/zephyr/admin/sell/invoice?id=" + invoice.getId();
        }
        if (count == 5) {
            model.addAttribute("errorMessage", "bạn chỉ có thể tạo đối đa 5 hoá đơn!");
        }
        return "redirect:/zephyr/admin/sell/index";
    }

    @GetMapping("/invoice")
    public String invoiceDetail(@RequestParam("id") Long id,
                                Model model) {
        Invoice invoice = invoiceService.detail(id);

        List<Double> listtotalInvoice = detailedInvoiceService.capitalSumDetailInvoice(id);
        Double totalInvoice = listtotalInvoice.get(0);

        model.addAttribute("listInvoiceStatus0", invoiceService.findAllByStatus0());
        model.addAttribute("listDetailProduct", productDetailsService.findAllByDisplaySell());
        model.addAttribute("listDetailInvoice", detailedInvoiceService.findAllByIdInvoice(id));
        model.addAttribute("listInvoice", invoiceService.findAllByInvoice(id));
        model.addAttribute("totalInvoice", totalInvoice);
        model.addAttribute("listVoucherPrice", voucherService.findAllByPrice(totalInvoice));
        model.addAttribute("listDetailProductUpdate", voucherService.findAllByPrice(totalInvoice));
        model.addAttribute("listSize", sizeService.getAll());
        model.addAttribute("listColor", colorService.getAll());
        model.addAttribute("view", "/WEB-INF/view/offline_sell/invoice.jsp");
        return "home/staff";
    }

    @PostMapping("/less")
    public String less(@RequestParam("id") Long id,
                       Model model) {

        DetailedInvoice detail = detailedInvoiceService.detail(id);
        Integer getQuantity = 0;
        DetailedInvoice detailedInvoice = detailedInvoiceService.detail(id);
        getQuantity = detailedInvoice.getQuantity() - 1;
        detailedInvoice.setQuantity(getQuantity);
        detailedInvoice.setCapitalSum(getQuantity * detail.getUnitPrice());
        detailedInvoiceService.update(detailedInvoice, detailedInvoice.getId());

        List<Double> listtotalInvoice = detailedInvoiceService.capitalSumDetailInvoice(detail.getInvoice().getId());
        Double totalInvoice = listtotalInvoice.get(0);
        Invoice invoice = invoiceService.detail(detail.getInvoice().getId());

        Double point = 0.0;
        if (String.valueOf(invoice.getPoint()).equalsIgnoreCase("null")) {
            point = 0.00;
        } else {
            point = invoice.getPoint();
        }

        Double voucher = 0.0;
        if (String.valueOf(invoice.getVoucher()).equalsIgnoreCase("null")) {
            voucher = 0.00;
        } else {
            voucher = invoice.getVoucher().getReducedPrice();
        }

        Double intoMoney = totalInvoice - point - voucher;

        invoice.setTotalInvoice(intoMoney);
        invoice.setIntoMoney(intoMoney);

        if (getQuantity == 0) {
            detailedInvoiceService.delete(id);
            return "redirect:/zephyr/admin/sell/invoice?id=" + detail.getInvoice().getId();
        }
        invoiceService.update(invoice, invoice.getId());


        return "redirect:/zephyr/admin/sell/invoice?id=" + detail.getInvoice().getId();
    }

    @PostMapping("/plus")
    public String plus(@RequestParam("id") Long id,
                       Model model) {

        DetailedInvoice detail = detailedInvoiceService.detail(id);
        Integer getQuantity = 0;
        DetailedInvoice detailedInvoice = detailedInvoiceService.detail(id);
        getQuantity = detailedInvoice.getQuantity() + 1;
        detailedInvoice.setQuantity(getQuantity);
        detailedInvoice.setCapitalSum(getQuantity * detail.getUnitPrice());
        detailedInvoiceService.update(detailedInvoice, detailedInvoice.getId());

        List<Double> listtotalInvoice = detailedInvoiceService.capitalSumDetailInvoice(detail.getInvoice().getId());
        Double totalInvoice = listtotalInvoice.get(0);
        Invoice invoice = invoiceService.detail(detail.getInvoice().getId());

        Double point = 0.0;
        if (String.valueOf(invoice.getPoint()).equalsIgnoreCase("null")) {
            point = 0.00;
        } else {
            point = invoice.getPoint();
        }

        Double voucher = 0.0;
        if (String.valueOf(invoice.getVoucher()).equalsIgnoreCase("null")) {
            voucher = 0.00;
        } else {
            voucher = invoice.getVoucher().getReducedPrice();
        }

        Double intoMoney = totalInvoice - point - voucher;
        invoice.setTotalInvoice(intoMoney);
        invoice.setIntoMoney(intoMoney);

        if (getQuantity == 0) {
            detailedInvoiceService.delete(id);
            return "redirect:/zephyr/admin/sell/invoice?id=" + detail.getInvoice().getId();
        }
        invoiceService.update(invoice, invoice.getId());

        return "redirect:/zephyr/admin/sell/invoice?id=" + detail.getInvoice().getId();
    }

    @GetMapping("/invoice/client-add")
    public String clientAdd(@RequestParam("id") Long id,
                            @RequestParam("phoneNumber") String phoneNumber) {

        Client client = clientService.detailPhoneNumber(phoneNumber);
        Invoice invoice = invoiceService.detail(id);
        invoice.setClient(client);
        invoiceService.update(invoice, invoice.getId());
        return "redirect:/zephyr/admin/sell/invoice?id=" + invoice.getId();
    }

    @GetMapping("/invoice/add-point-use")
    public String addPointUse(@RequestParam("id") Long id) {
        Double intoMoney = 0.0;
        Double voucher = 0.0;
        Invoice invoice = invoiceService.detail(id);
        if (String.valueOf(invoice.getVoucher()).equalsIgnoreCase("null")) {
            voucher = 0.00;
        } else {
            voucher = invoice.getVoucher().getReducedPrice();
        }
        intoMoney = invoice.getTotalInvoice() - invoice.getClient().getPointUsr() - voucher;

        invoice.setIntoMoney(intoMoney);
        invoice.setPoint(invoice.getClient().getPointUsr());
        invoiceService.update(invoice, invoice.getId());
        return "redirect:/zephyr/admin/sell/invoice?id=" + invoice.getId();
    }

    @GetMapping("/invoice/add-voucher")
    public String addVoucher(@RequestParam("id") Long id,
                             @RequestParam("voucher") Long idVoucher) {
        Double point = 0.0;
        Voucher voucher = voucherService.detail(idVoucher);
        Invoice invoice = invoiceService.detail(id);
        if (String.valueOf(invoice.getPoint()).equalsIgnoreCase("null")) {
            point = 0.00;
        } else {
            point = invoice.getPoint();
        }
        Double intoMoney = invoice.getTotalInvoice() - voucher.getReducedPrice() - point;

        invoice.setIntoMoney(intoMoney);
        invoice.setVoucher(voucher);
        invoiceService.update(invoice, invoice.getId());

        return "redirect:/zephyr/admin/sell/invoice?id=" + invoice.getId();
    }

    @GetMapping("/add-product")
    public String addProduct(@RequestParam("id") Long id,
                             @RequestParam("size") Long idSize,
                             @RequestParam("color") Long idColor,
                             @RequestParam("idInvoice") Long idInvoice,
                             @RequestParam("quantity") Integer quantity,
                             Model model) {

        Invoice invoice = invoiceService.detail(idInvoice);

        List<ProductDetails> list = productDetailsService.findAllByProductAndColorAndSize(id, idSize, idColor);
        List<Double> listtotalInvoice = detailedInvoiceService.capitalSumDetailInvoice(idInvoice);
        Double totalInvoice = listtotalInvoice.get(0);

        Double nullTotal = 0.0;
        if (String.valueOf(totalInvoice).equalsIgnoreCase("null")) {
            nullTotal = 0.0;
        } else {
            nullTotal = totalInvoice;
        }

        Double point = 0.0;
        if (String.valueOf(invoice.getPoint()).equalsIgnoreCase("null")) {
            point = 0.00;
        } else {
            point = invoice.getPoint();
        }

        Double voucher = 0.0;
        if (String.valueOf(invoice.getVoucher()).equalsIgnoreCase("null")) {
            voucher = 0.00;
        } else {
            voucher = invoice.getVoucher().getReducedPrice();
        }


        for (ProductDetails details : list) {
            Double sum = quantity * details.getPrice();
            Double total = sum + nullTotal;

            DetailedInvoice detailedInvoice = DetailedInvoice.builder()
                    .quantity(quantity)
                    .unitPrice(details.getPrice())
                    .capitalSum(sum)
                    .status(1)
                    .invoice(invoice)
                    .productDetails(details)
                    .build();
            detailedInvoiceService.add(detailedInvoice);

            Double intoMoney = total - point - voucher;
            invoice.setTotalInvoice(total);
            invoice.setIntoMoney(intoMoney);
            invoiceService.update(invoice, idInvoice);

            ProductDetails productDetails = productDetailsService.detail(detailedInvoice.getProductDetails().getId());
            productDetails.setInventory(productDetails.getInventory() - quantity);
            productDetailsService.update(productDetails, productDetails.getId());


        }
        return "redirect:/zephyr/admin/sell/invoice?id=" + invoice.getId();
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {

        List<Invoice> list = invoiceService.findAllByIdDetailInvoice(id);
        Invoice invoice = list.isEmpty() ? null : list.get(0);

        Double point = 0.0;
        if (String.valueOf(invoice.getPoint()).equalsIgnoreCase("null")) {
            point = 0.00;
        } else {
            point = invoice.getPoint();
        }

        Double voucher = 0.0;
        if (String.valueOf(invoice.getVoucher()).equalsIgnoreCase("null")) {
            voucher = 0.00;
        } else {
            voucher = invoice.getVoucher().getReducedPrice();
        }

        List<Double> listtotalInvoice = detailedInvoiceService.capitalSumDetailInvoice(invoice.getId());
        Double totalInvoice = listtotalInvoice.get(0);
        DetailedInvoice detailedInvoice = detailedInvoiceService.detail(id);
        Double intoMoney = totalInvoice - point - voucher - detailedInvoice.getCapitalSum();

        invoice.setTotalInvoice(intoMoney);
        invoice.setIntoMoney(intoMoney);
        invoiceService.update(invoice, invoice.getId());

        ProductDetails productDetails = productDetailsService.detail(detailedInvoice.getProductDetails().getId());
        productDetails.setInventory(productDetails.getInventory() + detailedInvoice.getQuantity());
        productDetailsService.update(productDetails, productDetails.getId());


        detailedInvoiceService.delete(id);
        return "redirect:/zephyr/admin/sell/invoice?id=" + invoice.getId();
    }

    @PostMapping("/invoice/update")
    public String updateInvoice(@RequestParam("code") String code,
                                @RequestParam("hourMinute") String hourMinute,
                                @RequestParam("dateCreate") LocalDate dateCreate,
                                @RequestParam("totalInvoice") Double totalInvoice,
                                @RequestParam("point") Double point,
                                @RequestParam("intoMoney") Double intoMoney,
                                @RequestParam("clientGiveMoney") Double clientGiveMoney,
                                @RequestParam("returnClientMoney") Double returnClientMoney,
                                @RequestParam("note") String note,
                                Model model, HttpSession session){

        Invoice invoice = invoiceService.detailCode(code);

        invoice.setTotalInvoice(totalInvoice);
        invoice.setPoint(point);
        invoice.setIntoMoney(intoMoney);
        invoice.setClientGiveMoney(clientGiveMoney);
        invoice.setReturnClientMoney(returnClientMoney);
        invoice.setNote(note);
        invoice.setStatus(5);
        invoiceService.update(invoice, invoice.getId());

        Voucher voucher = voucherService.detail(invoice.getVoucher().getId());
        voucher.setQuantity(voucher.getQuantity() - 1);
        voucherService.update(voucher, voucher.getId());

        return "redirect:/zephyr/admin/sell/index";
    }
}

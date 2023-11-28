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
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
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

    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        Staff staff = (Staff) session.getAttribute("staffSession");
        if (String.valueOf(staff).equalsIgnoreCase("null")) {
            return "redirect:/zephyr/admin/login";
        }
        List<Invoice> list = invoiceService.findAllByStaffStatus0(staff.getId());
        int count = list.size();
        if (count == 5) {
            model.addAttribute("errorMessage", "bạn chỉ có thể tạo đối đa 5 hoá đơn!");
        }
        model.addAttribute("listInvoiceStatus0", invoiceService.findAllByStaffStatus0(staff.getId()));
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
        List<Invoice> list = invoiceService.findAllByStaffStatus0(staff.getId());

        int count = list.size();
        for (int i = 0; count < 5; i++) {
            Invoice invoice = Invoice.builder()
                    .code(getNextCode())
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
                                Model model,HttpSession session) {
        Staff staff = (Staff) session.getAttribute("staffSession");
        if (String.valueOf(staff).equalsIgnoreCase("null")) {
            return "redirect:/zephyr/admin/login";
        }
        Invoice invoice = invoiceService.detail(id);

        List<Double> listtotalInvoice = detailedInvoiceService.capitalSumDetailInvoice(id);
        Double totalInvoice = listtotalInvoice.get(0);

        model.addAttribute("listInvoiceStatus0", invoiceService.findAllByStaffStatus0(staff.getId()));
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
                                Model model, HttpSession session) {

        Invoice invoice = invoiceService.detailCode(code);

        invoice.setTotalInvoice(totalInvoice);
        invoice.setPoint(point);
        invoice.setIntoMoney(intoMoney);
        invoice.setClientGiveMoney(clientGiveMoney);
        invoice.setReturnClientMoney(returnClientMoney);
        invoice.setNote(note);
        invoice.setStatus(5);
        invoiceService.update(invoice, invoice.getId());

        exportToWord(invoice);
        return "redirect:/zephyr/admin/sell/index";
    }

    @GetMapping("/invoice/delete")
    public String invoiceDelete(@RequestParam("id") Long id){
        invoiceService.delete(id);
        return "redirect:/zephyr/admin/sell/index";
    }

    public void exportToWord(Invoice invoice) {
        List<DetailedInvoice> list = detailedInvoiceService.findAllByIdInvoice(invoice.getId());

        XWPFDocument document = new XWPFDocument();

        XWPFParagraph title1 = document.createParagraph();
        title1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun1 = title1.createRun();
        titleRun1.setText("Z E P H Y R");
        titleRun1.setBold(true);
        titleRun1.setFontSize(30);
        titleRun1.setColor("00FF00");

        document.createParagraph().setSpacingAfter(10);

        XWPFParagraph infoParagraphZEPhYR = document.createParagraph();
        XWPFRun infoRunZ = infoParagraphZEPhYR.createRun();
        infoRunZ.setText("................................................................................" +
                "........................................................................................");

        document.createParagraph().setSpacingAfter(10);

        XWPFParagraph infoParagraphAddress = document.createParagraph();
        infoParagraphAddress.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun infoRunAddress = infoParagraphAddress.createRun();
        infoRunAddress.setText("Toà nhà FPT Polytechnic, Phố Trịnh Văn Bô, Nam Từ Liêm, Hà Nội");

        document.createParagraph().setSpacingAfter(20);

        XWPFParagraph title = document.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = title.createRun();
        titleRun.setText("Hóa đơn bán hàng");
        titleRun.setBold(true);
        titleRun.setFontSize(16);

        document.createParagraph().setSpacingAfter(20);

        XWPFParagraph infoParagraph = document.createParagraph();
        XWPFRun infoRun = infoParagraph.createRun();
        infoRun.setText("Hóa đơn: " + invoice.getCode() + "\t\t\t\t\t\t\t");
        infoRun.setText("Ngày Bán: " + invoice.getDateCreate() + " " + invoice.getHourMinute());

        XWPFParagraph infoParagraph1 = document.createParagraph();
        XWPFRun infoRun1 = infoParagraph1.createRun();
        infoRun1.setText("NVBH: " + invoice.getStaff().getId() + "\t\t\t\t");

        XWPFTable table = document.createTable();
        XWPFTableRow row1 = table.getRow(0);
        row1.getCell(0).setText("Mặt hàng" + "\t\t");
        row1.addNewTableCell().setText("Đơn giá" + "\t\t");
        row1.addNewTableCell().setText("Số lượng" + "\t\t");
        row1.addNewTableCell().setText("Thành Tiền");

        for (DetailedInvoice  detailedInvoice : list) {
            XWPFTableRow row = table.createRow();
            row.getCell(0).setText(detailedInvoice.getProductDetails().getProduct().getName() + "( " +
                    detailedInvoice.getProductDetails().getSize().getName() + ", " +
                    detailedInvoice.getProductDetails().getColor().getName() + " )" + "\t\t");
            row.getCell(1).setText(detailedInvoice.getProductDetails().getPrice() + "00" + "\t\t");
            row.getCell(2).setText(detailedInvoice.getQuantity() + "\t\t");
            row.getCell(3).setText(detailedInvoice.getCapitalSum() + "00");
        }

        document.createParagraph().setSpacingAfter(10);

        XWPFParagraph infoParagraphIntomoney = document.createParagraph();
        XWPFRun infoRunIntomoney = infoParagraphIntomoney.createRun();
        infoRunIntomoney.setText("................................................................................" +
                "........................................................................................");

        XWPFParagraph infoParagraph3 = document.createParagraph();
        infoParagraph3.setAlignment(ParagraphAlignment.RIGHT);
        XWPFRun infoRun3 = infoParagraph3.createRun();
        infoRun3.setText("Tổng tiền hóa đơn: " );
        infoRun3.setText("\t\t" + invoice.getTotalInvoice() + "00");

        if(!(String.valueOf(invoice.getPoint()).equalsIgnoreCase("0.0"))) {
            XWPFParagraph infoParagraph4 = document.createParagraph();
            infoParagraph4.setAlignment(ParagraphAlignment.RIGHT);
            XWPFRun infoRun4 = infoParagraph4.createRun();
            infoRun4.setText("Điểm sử dụng: ");
            infoRun4.setText("\t\t\t" + "-" + invoice.getPoint() + "00");
        }

        if(!(String.valueOf(invoice.getVoucher()).equalsIgnoreCase("null"))){
            XWPFParagraph infoParagraph5 = document.createParagraph();
            infoParagraph5.setAlignment(ParagraphAlignment.RIGHT);
            XWPFRun infoRun5 = infoParagraph5.createRun();
            infoRun5.setText("Giảm giá: " );
            infoRun5.setText("\t\t\t" + "-" + invoice.getVoucher().getReducedPrice() + "00");
        }

        XWPFParagraph infoParagraph10 = document.createParagraph();
        infoParagraph10.setAlignment(ParagraphAlignment.RIGHT);
        XWPFRun infoRun10 = infoParagraph10.createRun();
        infoRun10.setText("Thành tiền: " );
        infoRun10.setText("\t\t\t" + invoice.getIntoMoney() + "00");

        XWPFParagraph infoParagraph6 = document.createParagraph();
        infoParagraph6.setAlignment(ParagraphAlignment.RIGHT);
        XWPFRun infoRun6 = infoParagraph6.createRun();
        infoRun6.setText("Tổng tiền khách trả: " );
        infoRun6.setText("\t\t" + invoice.getClientGiveMoney() + "00");

        XWPFParagraph infoParagraph7 = document.createParagraph();
        infoParagraph7.setAlignment(ParagraphAlignment.RIGHT);
        XWPFRun infoRun7 = infoParagraph7.createRun();
        infoRun7.setText("Tổng tiền trả lại: " );
        infoRun7.setText("\t\t" + invoice.getReturnClientMoney() + "00");

        document.createParagraph().setSpacingAfter(20);

        XWPFParagraph infoParagraphENDIntomoney = document.createParagraph();
        XWPFRun infoRunENDIntomoney = infoParagraphENDIntomoney.createRun();
        infoRunENDIntomoney.setText("................................................................................" +
                "........................................................................................");

        document.createParagraph().setSpacingAfter(10);

        XWPFParagraph infoParagraph8 = document.createParagraph();
        infoParagraph8.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun infoRun8 = infoParagraph8.createRun();
        infoRun8.setText("CẢM ƠN QUÝ KHÁCH VÀ HẸN GẶP LẠI " );

        XWPFParagraph infoParagraph9 = document.createParagraph();
        infoParagraph9.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun infoRun9 = infoParagraph9.createRun();
        infoRun9.setText("Hotline: 0898629635 - Website: www.zephyr.com" );

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = sdf.format(new Date());
        String fileName = "invoice_" + timestamp + ".docx";

        try (FileOutputStream out = new FileOutputStream("C:\\Users\\BXT\\Desktop\\invoice\\" + fileName)) {
            document.write(out);
            System.out.println("Hóa đơn đã được xuất ra file Word thành công: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi xuất hóa đơn ra file Word.");
        }
    }

}

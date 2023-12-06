package com.example.demo.controller;

import com.example.demo.entity.DeliveryNotes;
import com.example.demo.entity.DetailDeliveryNotes;
import com.example.demo.entity.DetailedInvoice;
import com.example.demo.entity.Invoice;
import com.example.demo.entity.Staff;
import com.example.demo.service.DeliveryNotesService;
import com.example.demo.service.DetailDeliveryNotesService;
import com.example.demo.service.DetailedInvoiceService;
import com.example.demo.service.InvoiceService;
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

@Controller
@RequestMapping(value = "/zephyr/admin/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private DeliveryNotesService deliveryNotesService;

    @Autowired
    private DetailDeliveryNotesService detailDeliveryNotesService;

    @Autowired
    private DetailedInvoiceService detailedInvoiceService;

    @GetMapping("/status-all")
    public String statusAll(Model model, HttpSession session) {

        Staff staff = (Staff) session.getAttribute("staffSession");
        if (String.valueOf(staff).equalsIgnoreCase("null")) {
            return "redirect:/zephyr/admin/login";
        }
        model.addAttribute("listInvoiceStatusAll", invoiceService.findAllByIdStaffStatusAll(staff.getId()));
        model.addAttribute("view", "/WEB-INF/view/invoice/status-all.jsp");
        return "home/staff";
    }

    @GetMapping("/wait-for-confirmation")
    public String status2(Model model, HttpSession session) {

        Staff staff = (Staff) session.getAttribute("staffSession");
        if (String.valueOf(staff).equalsIgnoreCase("null")) {
            return "redirect:/zephyr/admin/login";
        }
        model.addAttribute("listInvoiceStatus02", invoiceService.findAllByStatus2());
        model.addAttribute("view", "/WEB-INF/view/invoice/status-02.jsp");
        return "home/staff";
    }

    @GetMapping("/received/status-5")
    public String status5(Model model, HttpSession session) {

        Staff staff = (Staff) session.getAttribute("staffSession");
        if (String.valueOf(staff).equalsIgnoreCase("null")) {
            return "redirect:/zephyr/admin/login";
        }

        model.addAttribute("listInvoiceStatus05", invoiceService.findAllByIdStaffStatus5(staff.getId()));
        model.addAttribute("view", "/WEB-INF/view/invoice/status-5.jsp");
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

        DetailDeliveryNotes detailDeliveryNotesStatus2 = DetailDeliveryNotes.builder()
                .detailNotesName("Đơn Hàng Đã Đặt")
                .progress("3.png")
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
                .progress("3.png")
                .hourMinute(currentTime.getHour() + ":" + currentTime.getMinute())
                .dateCreate(localDate)
                .note("không")
                .status(3)
                .invoice(invoice)
                .deliveryNotes(deliveryNotes)
                .build();
        detailDeliveryNotesService.add(detailDeliveryNotesStatus3);
        exportToWord(invoice);

        return "redirect:/zephyr/admin/invoice/wait-for-confirmation";
    }

    @GetMapping("/search")
    public String search(@RequestParam("inputInvoice") String inputInvoice,
                         Model model){

        model.addAttribute("listInvoiceStatusAll", invoiceService.findAllByInvoiceSearch(inputInvoice));
        model.addAttribute("view", "/WEB-INF/view/invoice/status-all.jsp");
        return "home/staff";
    }

    @GetMapping("/search-status")
    public String searchStatus(@RequestParam("status") Integer status,
                               Model model){

        model.addAttribute("listInvoiceStatusAll", invoiceService.findAllByStatusSearch(status));
        model.addAttribute("view", "/WEB-INF/view/invoice/status-all.jsp");
        return "home/staff";
    }

    @GetMapping("/search-date")
    public String searchDate(@RequestParam("dateBegin") LocalDate dateBegin,
                             @RequestParam("dateEnd") LocalDate dateEnd,
                             Model model){

        LocalDate localDate = LocalDate.now();

        if (dateEnd.isBefore(dateBegin)) {
            model.addAttribute("error", "Ngày kết thúc phải lớn hơn ngày bắt đầu.");
            model.addAttribute("view", "/WEB-INF/view/invoice/status-all.jsp");
            return "home/staff";
        }else if (dateEnd.isAfter(localDate)) {
            model.addAttribute("error", "Ngày kết thúc phải nhỏ hơn hoặc bằng ngày hiện tại.");
            model.addAttribute("view", "/WEB-INF/view/invoice/status-all.jsp");
            return "home/staff";
        }
        model.addAttribute("listInvoiceStatusAll", invoiceService.findAllByDateSearch(dateBegin, dateEnd));
        model.addAttribute("view", "/WEB-INF/view/invoice/status-all.jsp");
        return "home/staff";
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
        titleRun.setText("Phiếu giao hàng");
        titleRun.setBold(true);
        titleRun.setFontSize(16);

        document.createParagraph().setSpacingAfter(20);

        XWPFParagraph infoParagraph = document.createParagraph();
        XWPFRun infoRun = infoParagraph.createRun();
        infoRun.setText("Hóa đơn: " + invoice.getCode() + "\t\t\t\t\t\t\t");
        infoRun.setText("Ngày Bán: " + invoice.getDateCreate() + " " + invoice.getHourMinute());


        XWPFTable table0 = document.createTable();
        XWPFTableRow row0 = table0.getRow(0);
        row0.getCell(0).setText("Từ" + "\t\t\t\t\t\t");
        row0.addNewTableCell().setText("Đến");

        XWPFTableRow row00 = table0.createRow();
        row00.getCell(0).setText("Lê Huy Dương: " +
                "Toà nhà FPT Polytechnic" + ", " +
                "Phố Trịnh Văn Bô, Nam Từ Liêm, Hà Nội" + ", " +
                "0898829829");
        row00.getCell(1).setText(invoice.getAddress().getName() + ", " +
                invoice.getAddress().getPhoneNumber() + ", " +
                invoice.getAddress().getClientAddress() + ", " +
                invoice.getAddress().getCommune() + ", " +
                invoice.getAddress().getDistrict() + ", " +
                invoice.getAddress().getCity());

        document.createParagraph().setSpacingAfter(20);

        XWPFTable table = document.createTable();
        XWPFTableRow row1 = table.getRow(0);
        row1.getCell(0).setText("Mặt hàng" + "\t\t");
        row1.addNewTableCell().setText("Đơn giá" + "\t\t");
        row1.addNewTableCell().setText("Số lượng" + "\t\t");
        row1.addNewTableCell().setText("Thành Tiền");

        for (DetailedInvoice detailedInvoice : list) {
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
        infoRun3.setText("Tiền thu người nhận: ");
        infoRun3.setText("\t\t" + invoice.getTotalInvoice() + "00 VNĐ");

        XWPFParagraph infoParagraph4 = document.createParagraph();
        infoParagraph4.setAlignment(ParagraphAlignment.RIGHT);
        XWPFRun infoRun4 = infoParagraph4.createRun();
        infoRun4.setText("Chỉ dẫn giao hàng");

        XWPFParagraph infoParagraph5 = document.createParagraph();
        infoParagraph5.setAlignment(ParagraphAlignment.RIGHT);
        XWPFRun infoRun5 = infoParagraph5.createRun();
        infoRun5.setText("-Chỉ dẫn giao hàng");

        XWPFParagraph infoParagraph6 = document.createParagraph();
        infoParagraph6.setAlignment(ParagraphAlignment.RIGHT);
        XWPFRun infoRun6 = infoParagraph6.createRun();
        infoRun6.setText("Chuyển hoàn sau 3 lần phát");

        XWPFParagraph infoParagraph7 = document.createParagraph();
        infoParagraph7.setAlignment(ParagraphAlignment.RIGHT);
        XWPFRun infoRun7 = infoParagraph7.createRun();
        infoRun7.setText("-Lưu kho tối đa 5 ngày");

        document.createParagraph().setSpacingAfter(20);

        XWPFParagraph infoParagraphENDIntomoney = document.createParagraph();
        XWPFRun infoRunENDIntomoney = infoParagraphENDIntomoney.createRun();
        infoRunENDIntomoney.setText("................................................................................" +
                "........................................................................................");

        document.createParagraph().setSpacingAfter(10);

        XWPFParagraph infoParagraph8 = document.createParagraph();
        infoParagraph8.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun infoRun8 = infoParagraph8.createRun();
        infoRun8.setText("CẢM ƠN QUÝ KHÁCH");

        XWPFParagraph infoParagraph9 = document.createParagraph();
        infoParagraph9.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun infoRun9 = infoParagraph9.createRun();
        infoRun9.setText("Hotline: 0898629635 - Website: www.zephyr.com");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = sdf.format(new Date());
        String fileName = "notes_" + timestamp + ".docx";

        try (FileOutputStream out = new FileOutputStream("C:\\Users\\BXT\\Desktop\\notes\\" + fileName)) {
            document.write(out);
            System.out.println("Hóa đơn đã được xuất ra file Word thành công: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi xuất hóa đơn ra file Word.");
        }
    }


}

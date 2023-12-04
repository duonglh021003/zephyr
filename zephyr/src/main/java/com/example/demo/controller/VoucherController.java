package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.entity.Staff;
import com.example.demo.entity.VoucherClient;
import com.example.demo.service.VoucherClientService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Controller
@RequestMapping(value = "/zephyr/admin/voucher-client")
public class VoucherController {

    @Autowired
    private VoucherClientService voucherClientService;

    private String incrementCodeOrder(String code) {
        String prefix = code.substring(0, 2);
        int number = Integer.parseInt(code.substring(2));
        number++;
        String nextCode = String.format("%s%05d", prefix, number);
        return nextCode;
    }

    private String getNextCode() {
        String currentCode = voucherClientService.findMaxCodeVoucherClient();
        String nextCode = incrementCodeOrder(currentCode);
        return nextCode;
    }

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "0", name = "page") Integer number,
                        Model model, HttpSession session) {

        Staff staff = (Staff) session.getAttribute("staffSession");
        if (String.valueOf(staff).equalsIgnoreCase("null")) {
            return "redirect:/zephyr/admin/login";
        }
        LocalDate localDate = LocalDate.now();
        model.addAttribute("dateUpdate", localDate);

        Pageable pageable = PageRequest.of(number, 10);
        Page<VoucherClient> pageVoucherClient = voucherClientService.findAllByStatus1(pageable);
        model.addAttribute("listVoucherClient", pageVoucherClient);
        model.addAttribute("listVoucherClientStatus0", voucherClientService.findAllByStatus0());
        String staffName = (String) session.getAttribute("staffSession.name");
        model.addAttribute("staffSession", staffName);
        model.addAttribute("voucherClient", new VoucherClient());

        for (VoucherClient voucherClient : pageVoucherClient) {
            long day = ChronoUnit.DAYS.between(localDate, voucherClient.getDateEnd());
            if (day < 0) {
                voucherClient.setStatus(0);
                voucherClientService.update(voucherClient, voucherClient.getId());
            }

            if (voucherClient.getQuantity() == 0) {
                voucherClient.setStatus(0);
                voucherClientService.update(voucherClient, voucherClient.getId());
            }
        }

        model.addAttribute("view", "/WEB-INF/view/voucher/index.jsp");
        return "home/staff";
    }

    @PostMapping("/add")
    public String add(@RequestParam("quantity") Integer quantity,
                      @RequestParam("dateBegin") LocalDate dateBegin,
                      @RequestParam("dateEnd") LocalDate dateEnd,
                      @RequestParam("reducedPrice") Double reducedPrice,
                      @RequestParam("dateCreate") LocalDate dateCreate,
                      @RequestParam("dateUpdate") LocalDate dateUpdate,
                      @RequestParam("userCreate") String userCreate,
                      @RequestParam("userUpdate") String userUpdate,
                      @RequestParam("inRank") Long inRank,
                      @RequestParam("status") Integer status
    ) {

        VoucherClient voucherClient = VoucherClient.builder()
                .code(getNextCode())
                .quantity(quantity)
                .dateBegin(dateBegin)
                .dateEnd(dateEnd)
                .reducedPrice(reducedPrice)
                .dateCreate(dateCreate)
                .dateUpdate(dateUpdate)
                .userCreate(userCreate)
                .userUpdate(userUpdate)
                .inRank(inRank)
                .status(status)
                .build();
        voucherClientService.add(voucherClient);
        return "redirect:/zephyr/admin/voucher-client/index";
    }

    @GetMapping("/view-update")
    public String viewUpdate(@RequestParam("id") Long id,
                             Model model){

        LocalDate localDate = LocalDate.now();
        VoucherClient voucherClient = voucherClientService.detail(id);
        model.addAttribute("voucherClient", voucherClient);
        model.addAttribute("dateUpdate", localDate);

        model.addAttribute("view", "/WEB-INF/view/voucher/view-update.jsp");
        return "home/staff";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("voucherClient") VoucherClient voucherClient,
                         BindingResult result,
                         Model model) {

        if (result.hasErrors()) {
            LocalDate localDate = LocalDate.now();
            model.addAttribute("dateUpdate", localDate);
            model.addAttribute("view", "/WEB-INF/view/voucher/view-update.jsp");
            return "home/staff";
        }

        voucherClientService.update(voucherClient, voucherClient.getId());
        return "redirect:/zephyr/admin/voucher-client/index";
    }

    @GetMapping("/restore")
    public String restore(@RequestParam("id") Long id,
                          @RequestParam("quantity") Integer quantity,
                          @RequestParam("dateBegin") LocalDate dateBegin,
                          @RequestParam("dateEnd") LocalDate dateEnd
    ) {
        VoucherClient voucherClient = voucherClientService.detail(id);
        voucherClient.setQuantity(quantity);
        voucherClient.setDateBegin(dateBegin);
        voucherClient.setDateEnd(dateEnd);
        voucherClient.setStatus(1);
        voucherClientService.update(voucherClient, voucherClient.getId());
        return "redirect:/zephyr/admin/voucher-client/index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {

        VoucherClient details = voucherClientService.detail(id);
        details.setStatus(0);
        voucherClientService.update(details, details.getId());
        return "redirect:/zephyr/admin/voucher-client/index";
    }
}

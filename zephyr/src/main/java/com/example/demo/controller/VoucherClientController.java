package com.example.demo.controller;

import com.example.demo.entity.Client;
import com.example.demo.entity.DetailVoucherClient;
import com.example.demo.entity.VoucherClient;
import com.example.demo.repository.DetailVoucherClientRepository;
import com.example.demo.service.DetailVoucherClientService;
import com.example.demo.service.VoucherClientService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping(value = "/zephyr/voucher")
public class VoucherClientController {

    @Autowired
    private VoucherClientService voucherClientService;

    @Autowired
    private DetailVoucherClientService detailVoucherClientService;

    @Autowired
    private DetailVoucherClientRepository repository;

    private static final Random random = new Random();

    public static String generateRandomStringDetailVoucherClient() {
        StringBuilder sb = new StringBuilder(11);
        sb.append("voucher");
        for (int i = 0; i < 5; i++) {
            int rndNum = random.nextInt(10);
            sb.append(rndNum);
        }
        return sb.toString();
    }

    @GetMapping()
    public String index(Model model, HttpSession session) {
        Client client = (Client) session.getAttribute("clientSession");
        if (String.valueOf(client).equalsIgnoreCase("null")) {
            return "redirect:/zephyr/login";
        }
        List<VoucherClient> list = voucherClientService.findAllByInRank(client.getRank().getId());

        model.addAttribute("listVoucherClient", list);
        model.addAttribute("viewClient", "/WEB-INF/view/include/voucher.jsp");
        return "layout/client";
    }

    @GetMapping("/add")
    public String add(@RequestParam("id") Long id,
                      Model model, HttpSession session) {
        LocalDate localDate = LocalDate.now();
        VoucherClient voucherClient = voucherClientService.detail(id);
        Client client = (Client) session.getAttribute("clientSession");
        if (String.valueOf(client).equalsIgnoreCase("null")) {
            return "redirect:/zephyr/login";
        }

        List<VoucherClient> voucherClient1 = voucherClientService.findAllIdClientDuplicate(id, client.getId());

        if (voucherClient1.isEmpty()) {
            DetailVoucherClient detailVoucherClient = DetailVoucherClient.builder()
                    .code(generateRandomStringDetailVoucherClient())
                    .quantity(1)
                    .dateBegin(voucherClient.getDateBegin())
                    .dateEnd(voucherClient.getDateEnd())
                    .reducedPrice(voucherClient.getReducedPrice())
                    .dateCreate(localDate)
                    .status(1)
                    .client(client)
                    .voucherClient(voucherClient)
                    .build();
            detailVoucherClientService.add(detailVoucherClient);

            Integer getQuantity = voucherClient.getQuantity() - 1;
            voucherClient.setQuantity(getQuantity);
            voucherClientService.update(voucherClient, voucherClient.getId());
            return "redirect:/zephyr/voucher";
        } else {
            model.addAttribute("errorMessage", "bạn đã có hoặc đã sử phiếu này rồi!!! vui lòng chọn phiếu giảm giá khác.");
        }

        List<VoucherClient> list = voucherClientService.findAllByInRank(client.getRank().getId());

        model.addAttribute("listVoucherClient", list);
        model.addAttribute("viewClient", "/WEB-INF/view/include/voucher.jsp");
        return "layout/client";

    }


}

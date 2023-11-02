package com.example.demo.controller;

import com.example.demo.entity.Address;
import com.example.demo.entity.Client;

import com.example.demo.message.request.IdAddress;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.AddressService;
import com.example.demo.service.ClientService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping(value = "/zephyr/shop")
public class OrderController {

    @Autowired
    private ClientService clientService;


    @Autowired
    private AddressService addressService;

    LocalDate localDate = LocalDate.now();
    int codeLength = 7;
    String prefix = "ZA";
    String generatedCode = generateRandomCode(codeLength, prefix);

    public static String generateRandomCode(int codeLength, String prefix) {
        Random random = new Random();
        StringBuilder codeBuilder = new StringBuilder(prefix);

        for (int i = prefix.length(); i < codeLength; i++) {
            int randomDigit = random.nextInt(10);
            codeBuilder.append(randomDigit);
        }
        return codeBuilder.toString();
    }

    @GetMapping("/order")
    public String order(Model model, HttpSession session) {

        Client client = (Client) session.getAttribute("clientSession");
        if (String.valueOf(client).equalsIgnoreCase("null")) {
            return "redirect:/zephyr/login";
        }

        model.addAttribute("listAddresOrder", clientService.findAllById(client.getId()));
        model.addAttribute("listAddress", clientService.findAllByIdAndStatus(client.getId()));
        model.addAttribute("viewClient", "/WEB-INF/view/include/order.jsp");

        return "layout/client";
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
                .code(generatedCode)
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



}

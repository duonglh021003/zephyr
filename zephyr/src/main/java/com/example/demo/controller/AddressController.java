package com.example.demo.controller;

import com.example.demo.entity.Address;
import com.example.demo.entity.Client;
import com.example.demo.entity.Staff;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(value = "/zephyr/admin/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private ClientService clientService;

    @GetMapping("/view-update")
    public String viewupdate(@RequestParam("id") String id,
                             Model model,
                             HttpSession session) {

        String staffName = (String) session.getAttribute("staffSession.name");
        model.addAttribute("staffSession", staffName);
        model.addAttribute("staff", new Staff());
        Address address = addressService.detail(Long.valueOf(id));
        model.addAttribute("address", address);

        model.addAttribute("view", "/WEB-INF/view/address/view-update.jsp");
        return "home/staff";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("address") Address address,
                         @RequestParam("id") String id,
                         @RequestParam("name") String name,
                         @RequestParam("phoneNumber") String phoneNumber,
                         @RequestParam("clientAddress") String clientAddress,
                         @RequestParam("commune") String commune,
                         @RequestParam("district") String district,
                         @RequestParam("city") String city,
                         @RequestParam("status") Integer status,
                         Model model
    ) {
        Address idAddress = addressService.detail(Long.valueOf(id));
        Long idClient;
        Client client = Client.builder().id(idAddress.getClient().getId()).build();
        idClient = client.getId();
        List<Address> list = clientService.findAllById(idClient);
        for (Address p : list) {
            if (status.equals(1)) {
                if (p.getStatus().equals(1)) {
                    p.setStatus(0);
                }
            }
        }
        idAddress.setName(name);
        idAddress.setPhoneNumber(phoneNumber);
        idAddress.setClientAddress(clientAddress);
        idAddress.setCommune(commune);
        idAddress.setDistrict(district);
        idAddress.setCity(city);
        idAddress.setStatus(status);
        idAddress.setClient(idAddress.getClient());
        addressService.update(idAddress, Long.valueOf(id));

        return "redirect:/zephyr/admin/client/index?id=1";
    }
}

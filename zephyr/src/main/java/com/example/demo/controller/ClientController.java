package com.example.demo.controller;

import com.example.demo.entity.Client;
import com.example.demo.entity.Staff;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.RankRepository;
import com.example.demo.service.AddressService;
import com.example.demo.service.ClientService;
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

@Controller
@RequestMapping(value = "/zephyr/admin/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private RankRepository rankRepository;

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/index")
    public String index(@RequestParam("id") Long id,
                        @RequestParam(defaultValue = "0", name = "page") Integer number,
                        Model model,
                        HttpSession session) {

        String staffName = (String) session.getAttribute("staffSession.name");
        model.addAttribute("staffSession", staffName);
        Pageable pageable = PageRequest.of(number, 3);
        Page<Client> pageClient = clientService.getAll(pageable);
        model.addAttribute("listClient", pageClient);
        model.addAttribute("listAddress", clientService.findAllById(id));

        model.addAttribute("view", "/WEB-INF/view/client/index.jsp");

        return "home/staff";
    }

    @GetMapping("/index-page")
    public String page(@RequestParam(defaultValue = "0", name = "page") Integer number,
                       Model model,
                       HttpSession session) {

        String staffName = (String) session.getAttribute("staffSession.name");
        model.addAttribute("staffSession", staffName);
        Pageable pageable = PageRequest.of(number, 3);
        Page<Client> pageClient = clientService.getAll(pageable);
        model.addAttribute("listClient", pageClient);
        model.addAttribute("listAddress", "");
        model.addAttribute("view", "/WEB-INF/view/client/index.jsp");

        return "home/staff";
    }

    @GetMapping("/list-delete")
    public String listDelete(@RequestParam("id") Long id,
                             @RequestParam(defaultValue = "0", name = "page") Integer number,
                             Model model,
                             HttpSession session) {

        String staffName = (String) session.getAttribute("staffSession.name");
        model.addAttribute("staffSession", staffName);
        Pageable pageable = PageRequest.of(number, 3);
        Page<Client> pageClient = clientService.listDelete(pageable);
        model.addAttribute("listClient", pageClient);
        model.addAttribute("listAddress", clientService.findAllById(id));
        model.addAttribute("view", "/WEB-INF/view/client/list-delete.jsp");

        return "home/staff";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model,
                          HttpSession session) {

        LocalDate localDate = LocalDate.now();
        model.addAttribute("dateUpdate", localDate);
        String staffName = (String) session.getAttribute("staffSession.name");
        model.addAttribute("staffSession", staffName);
        model.addAttribute("client", new Client());

        model.addAttribute("listRank", rankRepository.findAll());
        model.addAttribute("view", "/WEB-INF/view/client/view-add.jsp");

        return "home/staff";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("client") Client client,
                      BindingResult result,
                      Model model,
                      HttpSession session) {

        if (result.hasErrors()) {
            LocalDate localDate = LocalDate.now();
            model.addAttribute("dateUpdate", localDate);
            String staffName = (String) session.getAttribute("staffSession.name");
            model.addAttribute("staffSession", staffName);
            model.addAttribute("listRank", rankRepository.findAll());
            model.addAttribute("view", "/WEB-INF/view/client/view-add.jsp");
            return "home/staff";
        } else {
            clientService.add(client);
        }

        return "redirect:/zephyr/admin/client/index?id=1";
    }

    @GetMapping("/view-update")
    public String viewupdate(@RequestParam("id") String id,
                             Model model,
                             HttpSession session) {

        LocalDate localDate = LocalDate.now();
        model.addAttribute("dateUpdate", localDate);
        String staffName = (String) session.getAttribute("staffSession.name");
        model.addAttribute("staffSession", staffName);
        model.addAttribute("client", new Client());
        Client client = clientService.detail(Long.valueOf(id));
        model.addAttribute("client", client);
        model.addAttribute("view", "/WEB-INF/view/client/view-update.jsp");

        return "home/staff";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("client") Client client,
                         @RequestParam("id") String id,
                         HttpSession session, Model model
    ) {

        String staffName = (String) session.getAttribute("staffSession.name");
        model.addAttribute("staffSession", staffName);
        clientService.update(client, Long.valueOf(id));
        return "redirect:/zephyr/admin/client/index?id=1";
    }


    @GetMapping("/delete")
    public String delete(@RequestParam("id") String id,
                         HttpSession session, Model model) {

        String staffName = (String) session.getAttribute("staffSession.name");
        model.addAttribute("staffSession", staffName);
        Client client = clientService.detail(Long.valueOf(id));
        client.setStatus(0);
        clientService.update(client, Long.valueOf(id));
        return "redirect:/zephyr/admin/client/index?id=1";

    }

    @GetMapping("/restore")
    public String restore(@RequestParam("id") String id,
                          HttpSession session, Model model) {

        String staffName = (String) session.getAttribute("staffSession.name");
        model.addAttribute("staffSession", staffName);
        Client client = clientService.detail(Long.valueOf(id));
        client.setStatus(1);
        clientService.update(client, Long.valueOf(id));
        return "redirect:/zephyr/admin/client/list-delete";
    }
}

package com.example.demo.controller;

import com.example.demo.entity.Color;
import com.example.demo.entity.Origin;
import com.example.demo.entity.Product;
import com.example.demo.entity.Staff;
import com.example.demo.service.OriginSerivce;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Random;

@Controller
@RequestMapping(value = "/zephyr/admin/origin")
public class OriginController {

    @Autowired
    private OriginSerivce originSerivce;

    private static final Random random = new Random();

    public static String generateRandomString() {
        StringBuilder sb = new StringBuilder(10);
        sb.append("product");
        for (int i = 0; i < 5; i++) {
            int rndNum = random.nextInt(10);
            sb.append(rndNum);
        }
        return sb.toString();
    }

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "0", name = "page") Integer number,
                        Model model, HttpSession session){
        Staff staff = (Staff) session.getAttribute("staffSession");
        if (String.valueOf(staff).equalsIgnoreCase("null")) {
            return "redirect:/zephyr/admin/login";
        }
        LocalDate localDate = LocalDate.now();
        Pageable pageable = PageRequest.of(number, 10);
        Page<Origin> pageOrigin = originSerivce.findAllByStatus1(pageable);
        model.addAttribute("listOrigin", pageOrigin);
        model.addAttribute("listRestore", originSerivce.findAllByStatus0());
        model.addAttribute("origin", new Origin());
        model.addAttribute("dateUpdate", localDate);

        model.addAttribute("view", "/WEB-INF/view/origin/index.jsp");
        return "home/staff";
    }

    @PostMapping("/add")
    public String add(@RequestParam("name") String name,
                      @RequestParam("dateCreate") LocalDate dateCreate,
                      @RequestParam("dateUpdate") LocalDate dateUpdate,
                      @RequestParam("userCreate") String userCreate,
                      @RequestParam("userUpdate") String userUpdate,
                      @RequestParam("status") Integer status) {

        Origin origin = Origin.builder()
                .code(generateRandomString())
                .name(name)
                .dateCreate(dateCreate)
                .dateUpdate(dateUpdate)
                .userCreate(userCreate)
                .userUpdate(userUpdate)
                .status(status)
                .build();
        originSerivce.add(origin);
        return "redirect:/zephyr/admin/origin/index";
    }

    @GetMapping("/view-update")
    public String viewUpdate(@RequestParam("id") Long id,Model model){

        LocalDate localDate = LocalDate.now();
        Origin origin = originSerivce.detail(id);
        model.addAttribute("origin", origin);
        model.addAttribute("dateUpdate", localDate);

        model.addAttribute("view", "/WEB-INF/view/origin/view-update.jsp");
        return "home/staff";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("origin") Origin origin){
        originSerivce.update(origin, origin.getId());
        return "redirect:/zephyr/admin/origin/index";
    }

    @GetMapping("/restore")
    public String restore(@RequestParam("id") Long id) {

        Origin origin = originSerivce.detail(id);
        origin.setStatus(1);
        originSerivce.update(origin, origin.getId());
        return "redirect:/zephyr/admin/origin/index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {

        Origin origin = originSerivce.detail(id);
        origin.setStatus(0);
        originSerivce.update(origin, origin.getId());
        return "redirect:/zephyr/admin/origin/index";
    }
}

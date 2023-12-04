package com.example.demo.controller;

import com.example.demo.config.GoogleUtils;
import com.example.demo.entity.Staff;
import com.example.demo.repository.PositionRepository;
import com.example.demo.service.StaffService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
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

import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;

@Controller
@RequestMapping(value = "/zephyr/admin/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @Autowired
    private PositionRepository positionRepository;

    private String incrementCodeOrder(String code) {
        String prefix = code.substring(0, 2);
        int number = Integer.parseInt(code.substring(2));
        number++;
        String nextCode = String.format("%s%05d", prefix, number);
        return nextCode;
    }

    private String getNextCode() {
        String currentCode = staffService.findMaxCodeStaff();
        String nextCode = incrementCodeOrder(currentCode);
        return nextCode;
    }

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "0", name = "page") Integer number,
                        Model model,
                        HttpSession session) {

        Staff staff = (Staff) session.getAttribute("staffSession");
        model.addAttribute("staffSession", staff);
        Pageable pageable = PageRequest.of(number, 10);
        Page<Staff> pageStaff = staffService.getAll(pageable);
        model.addAttribute("listStaff", pageStaff);
        model.addAttribute("listRestore", staffService.listDelete());
        model.addAttribute("view", "/WEB-INF/view/staff/index.jsp");

        return "home/staff";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model,
                          HttpSession session) {

        LocalDate localDate = LocalDate.now();
        model.addAttribute("dateUpdate", localDate);
        String staffName = (String) session.getAttribute("staffSession.name");
        model.addAttribute("staffSession", staffName);
        model.addAttribute("staff", new Staff());
        model.addAttribute("listPosition", positionRepository.findAll());
        model.addAttribute("view", "/WEB-INF/view/staff/view-add.jsp");
        return "home/staff";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("staff") Staff staff,
                      BindingResult result,
                      Model model,
                      HttpSession session) {
        if (result.hasErrors()) {
            LocalDate localDate = LocalDate.now();
            model.addAttribute("dateUpdate", localDate);
            String staffName = (String) session.getAttribute("staffSession.name");
            model.addAttribute("staffSession", staffName);
            model.addAttribute("listPosition", positionRepository.findAll());
            model.addAttribute("view", "/WEB-INF/view/staff/view-add.jsp");
            return "home/staff";
        } else {
            staff.setCode(getNextCode());
            staffService.add(staff);
        }

        return "redirect:/zephyr/admin/staff/index";
    }

    @GetMapping("/view-update")
    public String viewupdate(@RequestParam("id") String id,
                             Model model,
                             HttpSession session) {

        LocalDate localDate = LocalDate.now();
        model.addAttribute("dateUpdate", localDate);
        String staffName = (String) session.getAttribute("staffSession.name");
        model.addAttribute("staffSession", staffName);
        model.addAttribute("staff", new Staff());
        Staff staff = staffService.detail(Long.valueOf(id));
        model.addAttribute("staff", staff);
        model.addAttribute("listPosition", positionRepository.findAll());
        model.addAttribute("view", "/WEB-INF/view/staff/view-update.jsp");
        return "home/staff";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("staff") Staff staff,
                         @RequestParam("id") String id,
                         HttpSession session, Model model
    ) {

        String staffName = (String) session.getAttribute("staffSession.name");
        model.addAttribute("staffSession", staffName);
        staffService.update(staff, Long.valueOf(id));
        return "redirect:/zephyr/admin/staff/index";
    }

    @GetMapping("/restore")
    public String restore(@RequestParam("id") String id,
                          HttpSession session, Model model) {

        String staffName = (String) session.getAttribute("staffSession.name");
        model.addAttribute("staffSession", staffName);
        Staff staff = staffService.detail(Long.valueOf(id));
        staff.setStatus(1);
        staffService.update(staff, Long.valueOf(id));
        return "redirect:/zephyr/admin/staff/index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") String id,
                         HttpSession session, Model model) {

        String staffName = (String) session.getAttribute("staffSession.name");
        model.addAttribute("staffSession", staffName);

        Staff staff = staffService.detail(Long.valueOf(id));
        staff.setStatus(0);
        staffService.update(staff, Long.valueOf(id));
        return "redirect:/zephyr/admin/staff/index";

    }

}

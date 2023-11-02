package com.example.demo.controller;

import com.example.demo.entity.Staff;
import com.example.demo.repository.PositionRepository;
import com.example.demo.service.StaffService;
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
import java.util.Random;

@Controller
@RequestMapping(value = "/zephyr/admin/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @Autowired
    private PositionRepository positionRepository;



    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "0", name = "page") Integer number,
                        Model model,
                        HttpSession session) {

        Staff staff = (Staff) session.getAttribute("staffSession");
        model.addAttribute("staffSession", staff);
        System.out.println("MMMMMMMMMMMMMMMMMMMMMMM: " + staff);
        Pageable pageable = PageRequest.of(number, 6);
        Page<Staff> pageStaff = staffService.getAll(pageable);
        model.addAttribute("listStaff", pageStaff);
        model.addAttribute("view", "/WEB-INF/view/staff/index.jsp");

        return "home/staff";
    }

    @GetMapping("/list-delete")
    public String listDelete(@RequestParam(defaultValue = "0", name = "page") Integer number,
                             Model model,
                             HttpSession session) {

        String staffName = (String) session.getAttribute("staffSession.name");
        model.addAttribute("staffSession", staffName);
        Pageable pageable = PageRequest.of(number, 6);
        Page<Staff> pageStaff = staffService.listDelete(pageable);
        model.addAttribute("listStaff", pageStaff);
        model.addAttribute("view", "/WEB-INF/view/staff/list-delete.jsp");
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
        return "redirect:/zephyr/admin/staff/list-delete";
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

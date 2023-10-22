package com.example.demo.controller;

import com.example.demo.entity.Staff;
import com.example.demo.service.ProductService;
import com.example.demo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/zephyr")
@SessionAttributes({"phoneNumber", "password"})
public class HomePage {

    @Autowired
    private StaffService staffService;

    @Autowired
    ProductService service;

    @ModelAttribute("phoneNumber")
    public String getPhoneNumber(Model model) {
        return (String) model.getAttribute("phoneNumber");
    }

    @ModelAttribute("password")
    public String getPassword(Model model) {
        return (String) model.getAttribute("password");
    }

    // Kiểm tra Đã Đăng Nhập Chưa
    public String login(String phoneNumber, String password, Model model) {
        Staff staff = service.detailStaffByPhoneNumberAndPassword(phoneNumber, password);
        if (staff == null) {
            return "login/staff";
        } else if (phoneNumber.equalsIgnoreCase(staff.getPhoneNumber()) && password.equalsIgnoreCase(staff.getPassword())) {
            // Update the session attributes with the login information
            model.addAttribute("phoneNumber", phoneNumber);
            model.addAttribute("password", password);
            return "redirect:/zephyr/staff/home-page";
        } else {
            return "login/staff";
        }
    }

    @GetMapping("/staff/home-page")
    public String admin(Model model,
                        @ModelAttribute("phoneNumber") String phoneNumber,
                        @ModelAttribute("password") String password) {
        Staff detailStaff = service.detailStaffByPhoneNumberAndPassword(phoneNumber, password);
        model.addAttribute("detailStaff", detailStaff);
        model.addAttribute("main", "/WEB-INF/view/login/table.jsp");
        return "home_page/staff";
    }


}

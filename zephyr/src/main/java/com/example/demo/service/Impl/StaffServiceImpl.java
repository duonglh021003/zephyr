package com.example.demo.service.Impl;

import com.example.demo.entity.Staff;
import com.example.demo.repository.StaffRepository;
import com.example.demo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public List<Staff> getAll() {
        return staffRepository.findAll();
    }

    @Override
    public Staff findStaff(String phoneNumber, String password) {
        return staffRepository.findStaffByPhoneNumberAndPassword(phoneNumber,password);
    }

    @Override
    public String login(String phoneNumber, String password) {
        Staff staff = staffRepository.findStaffByPhoneNumberAndPassword(phoneNumber,password);
        if (staff == null) {
            return "login/staff";
        } else if (phoneNumber.equalsIgnoreCase(staff.getPhoneNumber()) && password.equalsIgnoreCase(staff.getPassword())) {
            return "redirect:/zephyr/staff/home-page";
        } else {
            return "login/staff";
        }
    }
}

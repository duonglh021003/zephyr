package com.example.demo.service.Impl;

import com.example.demo.entity.Staff;
import com.example.demo.repository.StaffRepository;
import com.example.demo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;



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

    @Override
    public Staff detailPhone(String phoneNumber) {
        for(Staff staff: staffRepository.findAll()){
            if(staff.getPhoneNumber().equalsIgnoreCase(phoneNumber)){
                return staff;
            }
        }
        return null;
    }

    @Override
    public Page<Staff> getAll(Pageable pageable) {
        return staffRepository.findAllByStatus(1,pageable);
    }

    @Override
    public Page<Staff> listDelete(Pageable pageable) {
        return staffRepository.findAllByStatus(0,pageable);
    }

    @Override
    public void add(Staff staff) {
        staffRepository.save(staff);
    }

    @Override
    public void update(Staff staff, Long id) {
        staffRepository.save(staff);
    }

    @Override
    public Staff detail(Long id) {
        return staffRepository.getById(id);
    }


}

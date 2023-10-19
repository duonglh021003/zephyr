package com.example.demo.service;

import com.example.demo.entity.Staff;

import java.util.List;

public interface StaffService {

    List<Staff> getAll();

    Staff findStaff(String phoneNumber, String password);

    String login(String phoneNumber, String password);

    Staff detailPhone(String phoneNumber);

}

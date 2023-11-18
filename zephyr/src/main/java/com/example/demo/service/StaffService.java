package com.example.demo.service;

import com.example.demo.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StaffService {

    List<Staff> getAll();

    Staff findStaff(String phoneNumber, String password);

    String login(String phoneNumber, String password);

    Staff detailPhone(String phoneNumber);

    Page<Staff> getAll(Pageable pageable);

    Page<Staff> listDelete(Pageable pageable);

    void add(Staff staff);

    void update(Staff staff, Long id);

    Staff detail(Long id);

}

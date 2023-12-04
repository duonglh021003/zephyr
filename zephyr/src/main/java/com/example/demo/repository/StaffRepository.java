package com.example.demo.repository;

import com.example.demo.entity.Color;
import com.example.demo.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    Staff findStaffByPhoneNumberAndPassword(String phoneNumber, String password);

    Page<Staff> findAllByStatus(int status, Pageable pageable);

    @Query(value = "select *\n" +
            "from\n" +
            "staff s\n" +
            "where s.staff_status = 0\n" +
            "ORDER BY s.id DESC", nativeQuery = true)
    List<Staff> findAllByStatus0();

    @Query(value = "select max(code) from staff", nativeQuery = true)
    String findMaxCodeStaff();

}

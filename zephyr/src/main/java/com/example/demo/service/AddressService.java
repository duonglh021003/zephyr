package com.example.demo.service;

import com.example.demo.entity.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AddressService {

    List<Address> getAll();

    Page<Address> listDelete(Pageable pageable);

    void add(Address address);

    void update(Address address, Long id);

    Address detail(Long id);

    List<Address> findAllAddress(Long id);

    String findMaxCodeAddress();

    List<Address> findAllClientNull();
}

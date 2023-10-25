package com.example.demo.service.Impl;

import com.example.demo.entity.Address;
import com.example.demo.repository.AddressRepository;
import com.example.demo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    @Override
    public Page<Address> listDelete(Pageable pageable) {
        return null;
    }

    @Override
    public void add(Address address) {
        addressRepository.save(address);
    }

    @Override
    public void update(Address address, Long id) {
        addressRepository.save(address);
    }

    @Override
    public Address detail(Long id) {
        return addressRepository.getById(id);
    }
}

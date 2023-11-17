package com.example.demo.service.Impl;

import com.example.demo.entity.Size;
import com.example.demo.repository.SizeRepository;
import com.example.demo.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public List<Size> getAll() {
        return sizeRepository.findAll();
    }

    @Override
    public List<Size> findByNameSize(Long id) {
        return sizeRepository.findByNameSize(id);
    }
}

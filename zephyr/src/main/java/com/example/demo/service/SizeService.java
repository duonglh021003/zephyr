package com.example.demo.service;

import com.example.demo.entity.Size;

import java.util.List;

public interface SizeService {

    List<Size> findByNameSize(Long id);
}

package com.example.demo.service;

import com.example.demo.entity.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SizeService {

    List<Size> getAll();

    List<Size> findByNameSize(Long id);

    Page<Size> findAllByStatus1(Pageable pageable);

    List<Size> findAllByStatus0();

    void add(Size size);

    void update(Size size, Long id);

    Size detail(Long id);
}

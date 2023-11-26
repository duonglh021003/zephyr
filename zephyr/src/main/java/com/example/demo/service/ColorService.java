package com.example.demo.service;

import com.example.demo.entity.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ColorService {

    List<Color> getAll();

    Page<Color> findAllByStatus1(Pageable pageable);

    Page<Color> findAllByStatus0(Pageable pageable);
}

package com.example.demo.service;

import com.example.demo.entity.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ColorService {

    List<Color> getAll();

    Page<Color> findAllByStatus1(Pageable pageable);

    List<Color> findAllByStatus0();

    String findMaxCodeColor();

    void add(Color color);

    void update(Color color);

    Color detail(Long id);

    Page<Color> findAllByColorSearch(String inputColor, Pageable pageable);
}

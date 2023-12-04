package com.example.demo.service.Impl;

import com.example.demo.entity.Color;
import com.example.demo.repository.ColorRepository;
import com.example.demo.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public List<Color> getAll() {
        return colorRepository.findAll();
    }

    @Override
    public Page<Color> findAllByStatus1(Pageable pageable) {
        return colorRepository.findAllByStatus1(pageable);
    }

    @Override
    public List<Color> findAllByStatus0() {
        return colorRepository.findAllByStatus0();
    }

    @Override
    public String findMaxCodeColor() {
        return colorRepository.findMaxCodeColor();
    }

    @Override
    public void add(Color color) {
        colorRepository.save(color);
    }

    @Override
    public void update(Color color) {
        colorRepository.save(color);
    }

    @Override
    public Color detail(Long id) {
        return colorRepository.getById(id);
    }

    @Override
    public Page<Color> findAllByColorSearch(String inputColor, Pageable pageable) {
        return colorRepository.findAllByColorSearch(inputColor, pageable);
    }


}

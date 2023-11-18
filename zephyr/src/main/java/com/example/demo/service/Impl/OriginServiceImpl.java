package com.example.demo.service.Impl;

import com.example.demo.entity.Origin;
import com.example.demo.repository.OriginRepository;
import com.example.demo.service.OriginSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OriginServiceImpl implements OriginSerivce {

    @Autowired
    private OriginRepository originRepository;

    @Override
    public List<Origin> getAll() {
        return originRepository.findAll();
    }
}

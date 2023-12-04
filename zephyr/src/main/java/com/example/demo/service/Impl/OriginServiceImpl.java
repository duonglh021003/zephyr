package com.example.demo.service.Impl;

import com.example.demo.entity.Origin;
import com.example.demo.repository.OriginRepository;
import com.example.demo.service.OriginSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Page<Origin> findAllByStatus1(Pageable pageable) {
        return originRepository.findAllByStatus1(pageable);
    }

    @Override
    public List<Origin> findAllByStatus0() {
        return originRepository.findAllByStatus0();
    }

    @Override
    public void add(Origin origin) {
        originRepository.save(origin);
    }

    @Override
    public void update(Origin origin, Long id) {
        originRepository.save(origin);
    }

    @Override
    public Origin detail(Long id) {
        return originRepository.getById(id);
    }

    @Override
    public Page<Origin> findAllByOriginSearch(String inputOrigin, Pageable pageable) {
        return originRepository.findAllByOriginSearch(inputOrigin, pageable);
    }

    @Override
    public String findMaxCodeOrigin() {
        return originRepository.findMaxCodeOrigin();
    }

}

package com.example.demo.service.Impl;

import com.example.demo.entity.Position;
import com.example.demo.repository.PositionRepository;
import com.example.demo.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionRepository positionRepository;


    @Override
    public Page<Position> getAll(Pageable pageable) {
        return positionRepository.findAll(pageable);
    }

    @Override
    public void add(Position position) {
        positionRepository.save(position);
    }

    @Override
    public void update(Position position, Long id) {
        positionRepository.save(position);
    }


}

package com.example.demo.service;


import com.example.demo.entity.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PositionService {

    Page<Position> getAll(Pageable pageable);

    void add(Position position);

    void update(Position position, Long id);

}

package com.example.demo.service;

import com.example.demo.entity.Origin;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OriginSerivce {

    List<Origin> getAll();

    Page<Origin> findAllByStatus1(Pageable pageable);

    List<Origin> findAllByStatus0();

    void add(Origin origin);

    void update(Origin origin, Long id);

    Origin detail(Long id);

    Page<Origin> findAllByOriginSearch(String inputOrigin,Pageable pageable);

    String findMaxCodeOrigin();
}

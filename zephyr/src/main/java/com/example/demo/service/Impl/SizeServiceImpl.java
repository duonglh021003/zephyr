package com.example.demo.service.Impl;

import com.example.demo.entity.Size;
import com.example.demo.repository.SizeRepository;
import com.example.demo.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public List<Size> getAll() {
        return sizeRepository.findAll();
    }

    @Override
    public List<Size> findByNameSize(Long id) {
        return sizeRepository.findByNameSize(id);
    }

    @Override
    public Page<Size> findAllByStatus1(Pageable pageable) {
        return sizeRepository.findAllByStatus1(pageable);
    }



    @Override
    public List<Size> findAllByStatus0() {
        return sizeRepository.findAllByStatus0();
    }

    @Override
    public void add(Size size) {
        sizeRepository.save(size);
    }

    @Override
    public void update(Size size, Long id) {
        sizeRepository.save(size);
    }

    @Override
    public Size detail(Long id) {
        return sizeRepository.getById(id);
    }
}

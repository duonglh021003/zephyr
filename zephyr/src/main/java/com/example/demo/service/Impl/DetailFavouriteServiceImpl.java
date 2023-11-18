package com.example.demo.service.Impl;

import com.example.demo.entity.DetailsFavourite;
import com.example.demo.repository.DetailsFavouriteRepository;
import com.example.demo.service.DetailFavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailFavouriteServiceImpl implements DetailFavouriteService {

    @Autowired
    private DetailsFavouriteRepository detailsFavouriteRepository;

    @Override
    public List<DetailsFavourite> findAll() {
        return detailsFavouriteRepository.findAll();
    }

    @Override
    public void add(DetailsFavourite detailsFavourite) {
        detailsFavouriteRepository.save(detailsFavourite);
    }

    @Override
    public void delete(Long id) {
        detailsFavouriteRepository.deleteById(id);
    }

    @Override
    public List<DetailsFavourite> findAllById(Long id) {
        return detailsFavouriteRepository.findAllById(id);
    }

    @Override
    public DetailsFavourite detail(Long id) {
        return detailsFavouriteRepository.getById(id);
    }
}

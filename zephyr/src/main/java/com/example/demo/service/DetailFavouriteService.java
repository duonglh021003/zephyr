package com.example.demo.service;

import com.example.demo.entity.DetailsFavourite;

import java.util.List;

public interface DetailFavouriteService {

    List<DetailsFavourite> findAll();

    void add(DetailsFavourite detailsFavourite);

    void delete(Long id);

    List<DetailsFavourite> findAllById(Long id);

    DetailsFavourite detail(Long id);

}

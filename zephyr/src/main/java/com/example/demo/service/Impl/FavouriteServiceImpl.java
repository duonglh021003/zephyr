package com.example.demo.service.Impl;

import com.example.demo.entity.Favourite;
import com.example.demo.repository.FavouriteRepository;
import com.example.demo.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavouriteServiceImpl implements FavouriteService {

    @Autowired
    private FavouriteRepository favouriteRepository;

    @Override
    public String findMaxCodeFavourite() {
        return favouriteRepository.findMaxCodeFavourite();
    }

    @Override
    public void add(Favourite favourite) {
        favouriteRepository.save(favourite);
    }
}

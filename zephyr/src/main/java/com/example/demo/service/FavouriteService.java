package com.example.demo.service;

import com.example.demo.entity.Favourite;

public interface FavouriteService {

    String findMaxCodeFavourite();

    void add(Favourite favourite);
}

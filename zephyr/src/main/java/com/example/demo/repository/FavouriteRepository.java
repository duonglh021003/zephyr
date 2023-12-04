package com.example.demo.repository;

import com.example.demo.entity.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, Long> {

    @Query(value = "select max(code) from favourite", nativeQuery = true)
    String findMaxCodeFavourite();

}

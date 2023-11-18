package com.example.demo.repository;

import com.example.demo.entity.DetailsFavourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailsFavouriteRepository extends JpaRepository<DetailsFavourite, Long> {

    @Query(value = "select favouriteDetails.*\n" +
            "from favourite_details favouriteDetails\n" +
            "join  favourite f on favouriteDetails.id_favourite = f.id\n" +
            "where f.id = ?1", nativeQuery = true)
    List<DetailsFavourite> findAllById(@Param("id") Long id);


}

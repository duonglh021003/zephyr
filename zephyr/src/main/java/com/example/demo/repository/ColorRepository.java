package com.example.demo.repository;

import com.example.demo.entity.Color;
import com.example.demo.entity.Origin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {

    @Query(value = "select *\n" +
            "from\n" +
            "color c\n" +
            "where c.color_status = 1\n" +
            "ORDER BY c.id DESC", nativeQuery = true)
    Page<Color> findAllByStatus1(Pageable pageable);

    @Query(value = "select *\n" +
            "from\n" +
            "color c\n" +
            "where c.color_status = 0\n" +
            "ORDER BY c.id DESC", nativeQuery = true)
    Page<Color> findAllByStatus0(Pageable pageable);

}

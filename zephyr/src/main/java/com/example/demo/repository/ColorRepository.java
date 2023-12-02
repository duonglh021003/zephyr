package com.example.demo.repository;

import com.example.demo.entity.Color;
import com.example.demo.entity.Origin;
import com.example.demo.entity.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    List<Color> findAllByStatus0();

    @Query(value = "select *\n" +
            "from\n" +
            "color c \n" +
            "where c.code like %?1% \n" +
            "or c.color_name like %?1% \n" +
            "or c.date_create like %?1% \n" +
            "or c.date_update like %?1% \n" +
            "or c.user_create like %?1% \n" +
            "or c.user_update like %?1% \n" +
            "ORDER BY c.id DESC", nativeQuery = true)
    Page<Color> findAllByColorSearch(@Param("inputColor") String inputColor,
                                   Pageable pageable);

    @Query(value = "select max(code) from color", nativeQuery = true)
    String findMaxCodeColor();

}

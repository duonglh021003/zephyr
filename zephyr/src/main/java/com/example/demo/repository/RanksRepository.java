package com.example.demo.repository;

import com.example.demo.entity.Color;
import com.example.demo.entity.Ranks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RanksRepository extends JpaRepository<Ranks, Long> {
    @Query(value = "select * from ranks where staff_status= 1", nativeQuery = true)
    List<Ranks> viewCBBRanks();

    @Modifying
    @Transactional
    @Query(value = "update color set color_status= 0 where id= :idColor", nativeQuery = true)
    void deleteColor(@Param("idColor") Long idColor);
}

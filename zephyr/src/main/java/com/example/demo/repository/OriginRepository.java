package com.example.demo.repository;

import com.example.demo.entity.Origin;
import com.example.demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OriginRepository extends JpaRepository<Origin, Long> {

    @Query(value = "select *\n" +
            "from\n" +
            "origin o\n" +
            "where o.origin_status = 1\n" +
            "ORDER BY o.id DESC", nativeQuery = true)
    Page<Origin> findAllByStatus1(Pageable pageable);

    @Query(value = "select *\n" +
            "from\n" +
            "origin o\n" +
            "where o.origin_status = 0\n" +
            "ORDER BY o.id DESC", nativeQuery = true)
    List<Origin> findAllByStatus0();

}

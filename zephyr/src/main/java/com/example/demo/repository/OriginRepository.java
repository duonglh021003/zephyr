package com.example.demo.repository;

import com.example.demo.entity.Origin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OriginRepository extends JpaRepository<Origin, Long> {
    @Query(value = "select * from origin where origin_status= 1", nativeQuery = true)
    List<Origin> viewCBBOrigins();

    @Modifying
    @Transactional
    @Query(value = "update origin set origin_status= 0 where id= :idOrigin", nativeQuery = true)
    void deleteOrigin(@Param("idOrigin") Long idOrigin);
}

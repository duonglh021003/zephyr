package com.example.demo.repository;

import com.example.demo.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {
    @Query(value = "select * from size where size_status= 1", nativeQuery = true)
    List<Size> viewCBBSizes();

    @Modifying
    @Transactional
    @Query(value = "update size set size_status= 0 where id= :idSize", nativeQuery = true)
    void deleteSize(@Param("idSize") Long idSize);
}

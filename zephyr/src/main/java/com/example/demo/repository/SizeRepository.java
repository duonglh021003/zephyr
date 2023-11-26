package com.example.demo.repository;

import com.example.demo.entity.DetailedShoppingCart;
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
public interface SizeRepository extends JpaRepository<Size, Long> {

    @Query(value = "select s.*\n" +
            "from\n" +
            "product_details productDetail join size s on productDetail.id_size = s.id\n" +
            "where productDetail.id = ?1", nativeQuery = true)
    List<Size> findByNameSize(@Param("id") Long id);

    @Query(value = "select *\n" +
            "from\n" +
            "size s\n" +
            "where s.size_status = 1\n" +
            "ORDER BY s.id DESC", nativeQuery = true)
    Page<Size> findAllByStatus1(Pageable pageable);

    @Query(value = "select *\n" +
            "from\n" +
            "size s\n" +
            "where s.size_status = 0\n" +
            "ORDER BY s.id DESC", nativeQuery = true)
    List<Size> findAllByStatus0();


}

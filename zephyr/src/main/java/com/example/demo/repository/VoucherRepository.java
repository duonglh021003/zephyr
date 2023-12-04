package com.example.demo.repository;

import com.example.demo.entity.Invoice;
import com.example.demo.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {

    @Query(value = "select v.*\n" +
            "from\n" +
            "voucher v  \n" +
            "where ?1 BETWEEN v.minimum_price AND v.maximum_price;\n", nativeQuery = true)
    List<Voucher> findAllByPrice(@Param("price") Double price);


}

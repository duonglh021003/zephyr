package com.example.demo.repository;

import com.example.demo.entity.DetailVoucherClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailVoucherClientRepository extends JpaRepository<DetailVoucherClient, Long> {

    List<DetailVoucherClient> findAllByStatus(int status);

    @Query(value = "select vcd.*\n" +
            "from\n" +
            "voucher_client_detail vcd\n" +
            "where vcd.id_client = ?1 and vcd.voucher_client_detail_status = '1'", nativeQuery = true)
    List<DetailVoucherClient> findAllIdClient(@Param("client") Long client);




}

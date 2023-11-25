package com.example.demo.repository;

import com.example.demo.entity.DetailedShoppingCart;
import com.example.demo.entity.VoucherClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherClientRepository extends JpaRepository<VoucherClient, Long> {

    @Query(value = "select vc.*\n" +
            "from\n" +
            "voucher_client vc \n" +
            "where vc.in_ranks <= ?1", nativeQuery = true)
    List<VoucherClient> findAllInRank(@Param("rank") Long rank);

    @Query(value = "select vc.*\n" +
            "from\n" +
            "voucher_client_detail vcd join voucher_client vc on vcd.id_voucher_client = vc.id\n" +
            "where vc.id = :id and vcd.id_client = :client", nativeQuery = true)
    List<VoucherClient> findAllIdClientDuplicate(@Param("id") Long id,
                                                 @Param("client") Long client);


    @Query(value = "select *\n" +
            "from\n" +
            "voucher_client vc\n" +
            "where vc.voucher_client_status = 1", nativeQuery = true)
    Page<VoucherClient> findAllByStatus1(Pageable pageable);

    @Query(value = "select *\n" +
            "from\n" +
            "voucher_client vc\n" +
            "where vc.voucher_client_status = 0", nativeQuery = true)
    List<VoucherClient> findAllByStatus0();

}

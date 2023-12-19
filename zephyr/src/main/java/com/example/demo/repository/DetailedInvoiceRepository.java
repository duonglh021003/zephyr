package com.example.demo.repository;

import com.example.demo.entity.Address;
import com.example.demo.entity.DetailVoucherClient;
import com.example.demo.entity.DetailedInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailedInvoiceRepository extends JpaRepository<DetailedInvoice, Long> {

    @Query(value = "select di.*\n" +
            "from detailed_invoice di join invoice i on di.id_invoice = i.id\n" +
            "where i.id = ?1", nativeQuery = true)
    List<DetailedInvoice> findAllByIdInvoice(@Param("id") Long id);

    @Query(value = "select ca.*\n" +
            "from\n" +
            "invoice i join client_address ca on i.id_client_address = ca.id\n" +
            "where i.id = ?1", nativeQuery = true)
    List<Address> findAllAddress(@Param("id") Long id);

    @Query(value = "select SUM(capital_sum)\n" +
            "from\n" +
            "detailed_invoice di join invoice i on di.id_invoice = i.id\n" +
            "where i.id = ?1", nativeQuery = true)
    List<Double> capitalSumDetailInvoice(@Param("id") Long id);

    @Query(value = "select di.*\n" +
            "from detailed_invoice di join invoice i on di.id_invoice = i.id\n" +
            "where i.id = :idInvoice \n" +
            "and di.id_product_details = :idProductDetail", nativeQuery = true)
    DetailedInvoice findAllByIdInvoiceAndProductDetails(@Param("idInvoice") Long idInvoice,
                                                        @Param("idProductDetail") Long idProductDetail);
}

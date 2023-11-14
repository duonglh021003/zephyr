package com.example.demo.repository;

import com.example.demo.entity.DetailVoucherClient;
import com.example.demo.entity.DetailedShoppingCart;
import com.example.demo.entity.Invoice;
import com.example.demo.entity.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findAllByStatus(int status);

    @Query(value = "SELECT i.*\n" +
            "FROM invoice i\n" +
            "JOIN client c ON i.id_client = c.id\n" +
            "WHERE c.id = ?1 AND i.id = (\n" +
            "  SELECT MAX(id)\n" +
            "  FROM invoice\n" +
            "  WHERE id_client = c.id); \n" +
            "", nativeQuery = true)
    List<Invoice> findByInvoiceMax(@Param("id") Long id);

    @Query(value = "select i.*\n" +
            "from\n" +
            "invoice i\n" +
            "where i.id_client = ?1 ", nativeQuery = true)
    List<Invoice> findByInvoiceStatusAll(@Param("id") Long id);

    @Query(value = "select i.*\n" +
            "from\n" +
            "invoice i\n" +
            "where i.id_client = ?1 and i.invoice_status = '1'", nativeQuery = true)
    List<Invoice> findByInvoiceStatus1(@Param("id") Long id);

    @Query(value = "select i.*\n" +
            "from\n" +
            "invoice i\n" +
            "where i.id_client = ?1 and i.invoice_status = '2'", nativeQuery = true)
    List<Invoice> findByInvoiceStatus2(@Param("id") Long id);

    @Query(value = "select i.*\n" +
            "from\n" +
            "invoice i\n" +
            "where i.id_client = ?1 and i.invoice_status = '3'", nativeQuery = true)
    List<Invoice> findByInvoiceStatus3(@Param("id") Long id);

    @Query(value = "select i.*\n" +
            "from\n" +
            "invoice i\n" +
            "where i.id_client = ?1 and i.invoice_status = '4'", nativeQuery = true)
    List<Invoice> findByInvoiceStatus4(@Param("id") Long id);

    @Query(value = "select i.*\n" +
            "from\n" +
            "invoice i\n" +
            "where i.id_client = ?1 and i.invoice_status = '5'", nativeQuery = true)
    List<Invoice> findByInvoiceStatus5(@Param("id") Long id);

    @Query(value = "select i.*\n" +
            "from\n" +
            "invoice i\n" +
            "where i.id_client = ?1 and i.invoice_status = '6'", nativeQuery = true)
    List<Invoice> findByInvoiceStatus6(@Param("id") Long id);

    @Query(value = "select i.*\n" +
            "from\n" +
            "invoice i\n" +
            "where i.id_client = ?1 and i.invoice_status = '7'", nativeQuery = true)
    List<Invoice> findByInvoiceStatus7(@Param("id") Long id);


    @Query(value = "select i.*\n" +
            "from\n" +
            "invoice i\n" +
            "where i.id = ?1", nativeQuery = true)
    List<Invoice> findAllByInvoice(@Param("id") Long id);

    @Query(value = "select i.*\n" +
            "from invoice i\n" +
            "where i.id_staff = ?1 AND i.invoice_status != '5'", nativeQuery = true)
    List<Invoice> findAllByIdStaff(@Param("id") Long id);



}

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

    @Query(value = "select i.*\n" +
            "from invoice i\n" +
            "where i.id_staff = ?1 AND i.invoice_status = '5'", nativeQuery = true)
    List<Invoice> findAllByIdStaffStatus5(@Param("id") Long id);

    @Query(value = "select i.*\n" +
            "from invoice i\n" +
            "where i.id_staff = ?1", nativeQuery = true)
    List<Invoice> findAllByIdStaffStatusAll(@Param("id") Long id);


    // BEGIN THỐNG KÊ

    @Query(value = "SELECT SUM(quantity)\n" +
            "FROM invoice i join detailed_invoice di on i.id = di.id_invoice\n" +
            "WHERE CONVERT(DATE, date_create) = CONVERT(DATE, GETDATE()) " +
            "AND i.invoice_status = 5", nativeQuery = true)
    List<Integer> findAllStatisticalProductDay();

    @Query(value = "SELECT SUM(into_money)\n" +
            "FROM invoice i\n" +
            "WHERE CONVERT(DATE, date_create) = CONVERT(DATE, GETDATE()) " +
            "AND i.invoice_status = 5", nativeQuery = true)
    List<Double> findAllStatisticalIntoMoneyDAYPresent();

    @Query(value = "SELECT SUM(into_money)\n" +
            "FROM invoice i\n" +
            "WHERE MONTH(date_create) = MONTH(GETDATE()) " +
            "AND YEAR(date_create) = YEAR(GETDATE()) " +
            "AND i.invoice_status = 5", nativeQuery = true)
    List<Double> findAllStatisticalIntoMoneyMONTHPresent();

    @Query(value = "SELECT SUM(quantity)\n" +
            "FROM invoice i join detailed_invoice di on i.id = di.id_invoice\n" +
            "WHERE MONTH(date_create) = MONTH(GETDATE()) " +
            "AND YEAR(date_create) = YEAR(GETDATE()) " +
            "AND i.invoice_status = 5", nativeQuery = true)
    List<Integer> findAllStatisticalQuantityMONTHPresent();

    @Query(value = "SELECT SUM(into_money) AS total_amount\n" +
            "FROM invoice i\n" +
            "WHERE YEAR(date_create) = YEAR(GETDATE()) " +
            "AND i.invoice_status = 5", nativeQuery = true)
    List<Double> findAllStatisticalIntoMoneyYEARPresent();

    @Query(value = "SELECT SUM(quantity)\n" +
            "FROM invoice i join detailed_invoice di on i.id = di.id_invoice\n" +
            "WHERE YEAR(date_create) = YEAR(GETDATE()) AND i.invoice_status = 5", nativeQuery = true)
    List<Integer> findAllStatisticalQuantityYEARPresent();


    @Query(value = "SELECT i.*\n" +
            "FROM invoice i\n" +
            "WHERE CONVERT(DATE, date_create) = CONVERT(DATE, GETDATE()) " +
            "AND i.invoice_status = 5", nativeQuery = true)
    List<Invoice> findAllStatisticalInvoiceProductDay();

    // sô lượng hoá đơn trong 1 tháng hiện tại
    @Query(value = "SELECT i.*\n" +
            "FROM invoice i \n" +
            "WHERE MONTH(date_create) = MONTH(GETDATE()) " +
            "AND YEAR(date_create) = YEAR(GETDATE()) " +
            "AND i.invoice_status = 5", nativeQuery = true)
    List<Invoice> findAllStatisticalInvoiceMonth();

    // sô lượng hoá đơn trong 1 năm hiện tại
    @Query(value = "SELECT i.*\n" +
            "FROM invoice i \n" +
            "WHERE YEAR(date_create) = YEAR(GETDATE()) " +
            "AND i.invoice_status = 5", nativeQuery = true)
    List<Invoice> findAllStatisticalInvoiceYear();

    // thống kê tổng tiền trong 1 năm khi search
    @Query(value = "SELECT SUM(into_money) AS total_amount\n" +
            "FROM invoice i \n" +
            "WHERE YEAR(date_create) = ?1 AND i.invoice_status = 5", nativeQuery = true)
    List<Double> findAllStatisticalSearchYear(@Param("year") Integer year);

    // số lượng hoá đơn trong 1 năm khi search
    @Query(value = "SELECT i.*\n" +
            "FROM invoice i \n" +
            "WHERE YEAR(date_create) = ?1 AND i.invoice_status = 5", nativeQuery = true)
    List<Invoice> findAllStatisticalInvoiceSearchYear(@Param("year") Integer year);

    // số lượng sản phẩm trong 1 năm search
    @Query(value = "SELECT SUM(quantity)\n" +
            "FROM invoice i join detailed_invoice di on i.id = di.id_invoice\t\n" +
            "WHERE YEAR(date_create) = ?1 AND i.invoice_status = 5", nativeQuery = true)
    List<Integer> findAllStatisticalQuantitySearchYear(@Param("year") Integer year);

    //thống kê tổng tiền trong 1 thang khi search
    @Query(value = "SELECT SUM(into_money) AS total_amount\n" +
            "FROM invoice i\n" +
            "WHERE MONTH(date_create) = :month AND YEAR(date_create) = :year AND i.invoice_status = 5;", nativeQuery = true)
    List<Double> findAllStatisticalSearchMonth(@Param("month") Integer month,
                                               @Param("year") Integer year);

    // số lượng hoá đơn trong 1 năm khi search
    @Query(value = "SELECT i.*\n" +
            "FROM invoice i\n" +
            "WHERE MONTH(date_create) = :month AND YEAR(date_create) = :year AND i.invoice_status = 5", nativeQuery = true)
    List<Invoice> findAllStatisticalInvoiceSearchMonth(@Param("month") Integer month,
                                                       @Param("year") Integer year);

    // số lượng sản phẩm trong 1 tháng search
    @Query(value = "SELECT SUM(quantity)\n" +
            "FROM invoice i join detailed_invoice di on i.id = di.id_invoice\n" +
            "WHERE MONTH(date_create) = :month AND YEAR(date_create) = :year AND i.invoice_status = 5", nativeQuery = true)
    List<Integer> findAllStatisticalQuantitySearchMonth(@Param("month") Integer month,
                                                       @Param("year") Integer year);


    // END THỐNG KÊ


    // SELL OF

    @Query(value = "select i.*\n" +
            "from invoice i\n" +
            "where i.invoice_status = 0 ", nativeQuery = true)
    List<Invoice> findAllByStatus0();

    @Query(value = "select i.*\n" +
            "from\n" +
            "detailed_invoice di join invoice i on di.id_invoice = i.id\n" +
            "where di.id = ?1", nativeQuery = true)
    List<Invoice> findAllByIdDetailInvoice(@Param("id") Long id);

    @Query(value = "select *\n" +
            "from\n" +
            "invoice i\n" +
            "where i.invoice_status = 0 and i.id_staff = ?1 ", nativeQuery = true)
    List<Invoice> findAllByStaffStatus0(@Param("idStaff") Long idStaff);

    // END SELL OFF
}

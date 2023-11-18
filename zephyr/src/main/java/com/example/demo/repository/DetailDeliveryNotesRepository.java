package com.example.demo.repository;

import com.example.demo.entity.DetailDeliveryNotes;
import com.example.demo.entity.DetailedInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailDeliveryNotesRepository extends JpaRepository<DetailDeliveryNotes, Long> {

    @Query(value = "select ddn.*\n" +
            "from\n" +
            "detail_delivery_notes ddn join invoice i on ddn.id_invoice = i.id\n" +
            "where i.id_staff = ?1", nativeQuery = true)
    List<DetailDeliveryNotes> findAllDetailDeliveryNotesByIdStaff(@Param("id") Long id);

    @Query(value = "select ddn.*\n" +
            "from\n" +
            "detail_delivery_notes ddn join invoice i on ddn.id_invoice = i.id\n" +
            "join delivery_notes dn on ddn.id_delivery_notes = dn.id\n" +
            "where i.id = ?1  AND ddn.id = (\n" +
            "  SELECT MAX(id)\n" +
            "  FROM detail_delivery_notes\n" +
            "  WHERE id_invoice = i.id AND id_delivery_notes = dn.id)", nativeQuery = true)
    List<DetailDeliveryNotes> findAllDetailDeliveryNotesMax(@Param("id") Long id);

    @Query(value = "select ddn.*\n" +
            "from\n" +
            "detail_delivery_notes ddn join invoice i on ddn.id_invoice = i.id\n" +
            "where i.id = ?1", nativeQuery = true)
    List<DetailDeliveryNotes> findAllByIdInvoice(@Param("id") Long id);
}

package com.example.demo.service;

import com.example.demo.entity.DetailDeliveryNotes;

import java.util.List;

public interface DetailDeliveryNotesService {

    List<DetailDeliveryNotes> getAll();

    void add(DetailDeliveryNotes detailDeliveryNotes);

    List<DetailDeliveryNotes> findAllDetailDeliveryNotesByIdStaff(Long id);

    List<DetailDeliveryNotes> findAllDetailDeliveryNotesMax(Long id);

    List<DetailDeliveryNotes> findAllByIdInvoice(Long id);

    DetailDeliveryNotes detail(Long id);
}

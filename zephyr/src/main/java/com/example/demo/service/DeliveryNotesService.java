package com.example.demo.service;

import com.example.demo.entity.DeliveryNotes;

import java.util.List;

public interface DeliveryNotesService {

    List<DeliveryNotes> getAll();

    void add(DeliveryNotes deliveryNotes);

    DeliveryNotes detail(Long id);
}

package com.example.demo.service.Impl;

import com.example.demo.entity.DeliveryNotes;
import com.example.demo.repository.DeliveryNotesRepository;
import com.example.demo.service.DeliveryNotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryNotesServiceImpl implements DeliveryNotesService {

    @Autowired
    private DeliveryNotesRepository deliveryNotesRepository;

    @Override
    public List<DeliveryNotes> getAll() {
        return deliveryNotesRepository.findAll();
    }

    @Override
    public void add(DeliveryNotes deliveryNotes) {
        deliveryNotesRepository.save(deliveryNotes);
    }

    @Override
    public DeliveryNotes detail(Long id) {
        return deliveryNotesRepository.getById(id);
    }
}

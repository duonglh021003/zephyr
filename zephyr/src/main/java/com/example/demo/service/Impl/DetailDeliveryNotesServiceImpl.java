package com.example.demo.service.Impl;

import com.example.demo.entity.DetailDeliveryNotes;
import com.example.demo.repository.DetailDeliveryNotesRepository;
import com.example.demo.service.DetailDeliveryNotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailDeliveryNotesServiceImpl implements DetailDeliveryNotesService {

    @Autowired
    private DetailDeliveryNotesRepository detailDeliveryNotesRepository;

    @Override
    public List<DetailDeliveryNotes> getAll() {
        return detailDeliveryNotesRepository.findAll();
    }

    @Override
    public void add(DetailDeliveryNotes detailDeliveryNotes) {
        detailDeliveryNotesRepository.save(detailDeliveryNotes);
    }

    @Override
    public List<DetailDeliveryNotes> findAllDetailDeliveryNotesByIdStaff(Long id) {
        return detailDeliveryNotesRepository.findAllDetailDeliveryNotesByIdStaff(id);
    }

    @Override
    public List<DetailDeliveryNotes> findAllDetailDeliveryNotesMax(Long id) {
        return detailDeliveryNotesRepository.findAllDetailDeliveryNotesMax(id);
    }

    @Override
    public List<DetailDeliveryNotes> findAllByIdInvoice(Long id) {
        return detailDeliveryNotesRepository.findAllByIdInvoice(id);
    }

    @Override
    public DetailDeliveryNotes detail(Long id) {
        return detailDeliveryNotesRepository.getById(id);
    }
}

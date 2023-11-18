package com.example.demo.repository;

import com.example.demo.entity.DeliveryNotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryNotesRepository extends JpaRepository<DeliveryNotes, Long> {
}

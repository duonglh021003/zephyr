package com.example.demo.repository;

import com.example.demo.entity.Client;
import com.example.demo.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Modifying
    @Transactional
    @Query(value = "update client set staff_status= 0 where id= :idClient", nativeQuery = true)
    void deleteClient(@Param("idClient") Long idClient);
}

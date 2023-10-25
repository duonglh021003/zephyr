package com.example.demo.repository;

import com.example.demo.entity.Address;
import com.example.demo.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Page<Client> findAllByStatus(int status, Pageable pageable);

    @Query("select DISTINCT addres from Address addres\n" +
            "inner join  Client client on addres.client.id = client.id\n" +
            "where client.id = :id")
    List<Address> findAllById(@Param("id") Long id);

}

package com.example.demo.repository;

import com.example.demo.entity.Address;
import com.example.demo.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query(value = "select ca.*\n" +
            "from\n" +
            "invoice i join client_address ca on i.id_client_address = ca.id\n" +
            "where i.id = ?1", nativeQuery = true)
    List<Address> findAllAddress(@Param("id") Long id);

    @Query(value = "select max(code) from client_address", nativeQuery = true)
    String findMaxCodeAddress();

    @Query(value = "select *\n" +
            "from\n" +
            "client_address ca \n" +
            "where ca.id_client is null and ca.id = (\n" +
            "\t select max(id)\n" +
            "\t from\n" +
            "\t client_address\n" +
            "\t where address_status = 1)", nativeQuery = true)
    List<Address> findAllClientNull();
}

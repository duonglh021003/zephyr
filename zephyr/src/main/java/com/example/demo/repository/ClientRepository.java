package com.example.demo.repository;

import com.example.demo.entity.Address;
import com.example.demo.entity.Client;
import com.example.demo.entity.Product;
import com.example.demo.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findClientByGmailAndPassword(String gmail, String password);

    Client findStaffByGmail(String gmail);

    Page<Client> findAllByStatus(int status, Pageable pageable);

    @Query("select DISTINCT addres from Address addres\n" +
            "inner join  Client client on addres.client.id = client.id\n" +
            "where client.id = :id")
    List<Address> findAllById(@Param("id") Long id);

    @Query("select DISTINCT addres from Address addres\n" +
            "inner join  Client client on addres.client.id = client.id\n" +
            "where client.id = :id " +
            "AND addres.status = 1")
    List<Address> findAllByIdAndStatus(@Param("id") Long id);

    @Query(value = "select max(code) from client", nativeQuery = true)
    String findMaxCodeClient();

    @Query(value = "select *\n" +
            "from\n" +
            "client c\n" +
            "where c.client_status = 0\n" +
            "ORDER BY c.id DESC", nativeQuery = true)
    List<Client> findAllByStatus0();

}

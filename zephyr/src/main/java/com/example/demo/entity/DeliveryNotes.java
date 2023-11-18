package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "delivery_notes")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class DeliveryNotes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address_client")
    private String addressClient;

    @Column(name = "date_order")
    private LocalDate dateOrder;

    @Column(name = "date_deliver")
    private LocalDate dateDeliver;

    @Column(name = "date_receive")
    private LocalDate dateReceive;

    @Column(name = "note")
    private String note;

    @Column(name = "delivery_notes_status")
    private Integer status;

}

package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

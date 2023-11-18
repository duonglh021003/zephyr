package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "detail_delivery_notes")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class DetailDeliveryNotes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "detail_delivery_notes_name")
    private String detailNotesName;

    @Column(name = "progress")
    private String progress;

    @Column(name = "hour_minute")
    private String hourMinute;

    @Column(name = "date_create")
    private LocalDate dateCreate;

    @Column(name = "note")
    private String note;

    @Column(name = "delivery_notes_status")
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_invoice", referencedColumnName = "id")
    private Invoice invoice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_delivery_notes", referencedColumnName = "id")
    private DeliveryNotes deliveryNotes;

}

package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "payment")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "payment_name")
    private String name;
}

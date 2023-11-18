package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "favourite")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Favourite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "favourite_status")
    private Integer status;
}

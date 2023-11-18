package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "ranks")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "ranks_name")
    private String name;

    @Column(name = "ranks_percent")
    private Double percent;

    @Column(name = "staff_status")
    private Integer status;

}

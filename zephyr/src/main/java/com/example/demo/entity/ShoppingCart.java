package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "shopping_cart")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "total_shopping_cart")
    private Double totalShoppingCart;

    @Column(name = "shopping_cart_status")
    private Integer status;


}

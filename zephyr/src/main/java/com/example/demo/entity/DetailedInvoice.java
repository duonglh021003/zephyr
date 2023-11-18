package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "detailed_invoice")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class DetailedInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "capital_sum")
    private Double capitalSum;

    @Column(name = "detailed_shopping_cart_status")
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_invoice", referencedColumnName = "id")
    private Invoice invoice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product_details", referencedColumnName = "id")
    private ProductDetails productDetails;

    public Double subTotalDetailInvoice(){
        return this.unitPrice * this.quantity;
    }

}

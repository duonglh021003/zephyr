package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "product_details")
public class ProductDetail {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProductDetail;

    @Column(name = "images")
    private String images;

    @Column(name = "describe")
    private String describe;

    @Column(name = "inventory")
    private Integer inventory;

    @Column(name = "import_price")
    private BigDecimal importPrice;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "product_details_status")
    private Integer status;

    @ManyToOne()
    @JoinColumn(name = "id_product")
    private Product product;

    @ManyToOne()
    @JoinColumn(name = "id_origin")
    private Origin origin;

    @ManyToOne()
    @JoinColumn(name = "id_color")
    private Color color;

    @ManyToOne()
    @JoinColumn(name = "id_size")
    private Size size;

    public String getAllStatus() {
        if (this.status == 1) {
            return "Đang Còn Hàng";
        } else {
            return "Tạm Thời Hết Hàng";
        }
    }
}

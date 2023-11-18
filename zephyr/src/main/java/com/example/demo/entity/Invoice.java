package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "invoice")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "hour_minute")
    private String hourMinute;

    @Column(name = "date_create")
    private LocalDate dateCreate;

    @Column(name = "total_invoice")
    private Double totalInvoice;

    @Column(name = "point")
    private Double point;

    @Column(name = "shipping_money")
    private Double shippingMoney;

    @Column(name = "into_money")
    private Double intoMoney;

    @Column(name = "client_give_money")
    private Double clientGiveMoney;

    @Column(name = "return_client_money")
    private Double returnClientMoney;

    @Column(name = "note")
    private String note;

    @Column(name = "invoice_status")
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client", referencedColumnName = "id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client_address", referencedColumnName = "id")
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_voucher", referencedColumnName = "id")
    private Voucher voucher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_staff", referencedColumnName = "id")
    private Staff staff;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_payment", referencedColumnName = "id")
    private Payment payment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_voucher_client_detail", referencedColumnName = "id")
    private DetailVoucherClient detailVoucherClient;

    public String getByStatus(){
        String byStatus;
        if(status == 0){
            byStatus = "chưa thanh toán";
        }else if(status == 1){
            byStatus = "đặt hàng";
        }else if(status == 2){
            byStatus = "chờ xác nhận";
        }else if(status == 3){
            byStatus = "chờ lấy hàng";
        }else if(status == 4){
            byStatus = "đang giao hàng";
        }else if(status == 5){
            byStatus = "đã nhận hàng";
        }else if(status == 6){
            byStatus = "đã hoàn thành";
        }else if(status == 7){
            byStatus = "đã huỷ";
        }else {
            byStatus = "đổi hàng";
        }
        return byStatus;
    }

}


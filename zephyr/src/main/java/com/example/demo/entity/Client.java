package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
@Entity
@Table(name = "client")
public class Client {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

    @Column(name = "code")
    private String codeClient;

    @Column(name = "date_of_birth")
    private Date date;

    @Column(name = "phone_number")
    private String phone;

    @Column(name = "gmail")
    private String gmail;

    @Column(name = "gender")
    private String gender;

    @Column(name = "staff_password")
    private String staffPassword;

    @Column(name = "staff_status")
    private Integer staffStatusClient;

    @ManyToOne
    @JoinColumn(name = "id_ranks", referencedColumnName = "id")
    private Ranks ranks;
}

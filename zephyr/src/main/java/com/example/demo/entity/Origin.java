package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "origin")
public class Origin {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrigin;

    @Column(name = "code")
    private String codeOrigin;

    @Column(name = "origin_name")
    private String nameOrigin;

    @Column(name = "date_create")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateCreate;

    @Column(name = "date_update")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateUpdate;

    @Column(name = "user_create")
    private String userCreate;

    @Column(name = "user_update")
    private String userUpdate;

    @Column(name = "origin_status")
    private Integer status;

    public String getAllStatus() {
        if (this.status == 1) {
            return "Đang Hoạt Động";
        } else {
            return "Ngừng Hoạt Động";
        }
    }
}

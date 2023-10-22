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
@Table(name = "size")
public class Size {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSize;

    @Column(name = "code")
    private String codeSize;

    @Column(name = "size_name")
    private String nameSize;

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

    @Column(name = "size_status")
    private Integer status;

    public String getAllStatus() {
        if (this.status == 1) {
            return "Đang Hoạt Động";
        } else {
            return "Ngừng Hoạt Động";
        }
    }
}

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
@Table(name = "color")
public class Color {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idColor;

    @Column(name = "code")
    private String codeColor;

    @Column(name = "color_name")
    private String nameColor;

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

    @Column(name = "color_status")
    private Integer status;

    public String getAllStatus() {
        if (this.status == 1) {
            return "Đang Hoạt Động";
        } else {
            return "Ngừng Hoạt Động";
        }
    }
}

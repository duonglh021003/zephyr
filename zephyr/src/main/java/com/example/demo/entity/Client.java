package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Table(name = "client")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "client_name")
    private String name;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "gmail")
    private String gmail;

    @Column(name = "gender")
    private String gender;

    @Column(name = "point_use")
    private Double pointUsr;

    @Column(name = "accumulated_score")
    private Double accumulatedScore;

    @Column(name = "staff_password")
    private String password;

    @Column(name = "date_create")
    private LocalDate dateCreate;

    @Column(name = "date_update")
    private LocalDate dateUpdate;

    @Column(name = "user_create")
    private String userCreate;

    @Column(name = "user_update")
    private String userUpdate;

    @Column(name = "staff_status")
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ranks", referencedColumnName = "id")
    private Rank rank;

    public String clickRank() {
        String ranks;
        if (0 <= accumulatedScore && accumulatedScore <= 500) {
             ranks = "Đồng hành";
        } else if (501 <= accumulatedScore && accumulatedScore <= 1000) {
            ranks = "Thân thiết";
        } else if (1001 <= accumulatedScore && accumulatedScore <= 2000) {
            ranks = "Tri kỷ";
        } else {
            ranks = "Vip";
        }
        return ranks;
    }

    public Integer idRank(){
        Integer idranks;
        if(clickRank().equalsIgnoreCase("Đồng hành")){
            idranks = 1;
        }else if(clickRank().equalsIgnoreCase("Thân thiết")){
            idranks = 2;
        }else if(clickRank().equalsIgnoreCase("Tri kỷ")){
            idranks = 3;
        }else {
            idranks = 4;
        }
        return idranks;
    }

}

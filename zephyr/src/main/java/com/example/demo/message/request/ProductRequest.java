package com.example.demo.message.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRequest {

    private Long id;

    @NotBlank(message = "mã không được trống!")
    private String code;

    private LocalDate dateCreate;

    private LocalDate dateUpdate;

    private String userCreate;

    private String userUpdate;

    private Integer status;


}

package com.example.demo.service;

import com.example.demo.entity.Login;

import java.util.List;

public interface LoginService {

    List<Login> getAll();

    void add(Login login);

}

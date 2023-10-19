package com.example.demo.service.Impl;

import com.example.demo.entity.Login;
import com.example.demo.service.LoginService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    ArrayList<Login> logins = new ArrayList<>();

    @Override
    public List<Login> getAll() {
        return logins;
    }

    @Override
    public void add(Login login) {
        logins.add(login);
    }



}

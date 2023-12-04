package com.example.demo.controller;

import com.example.demo.entity.Client;
import com.example.demo.entity.GooglePojo;
import com.example.demo.entity.Staff;
import com.example.demo.service.ClientService;
import com.example.demo.config.GoogleUtils;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequestMapping(value = "/zephyr")
public class LoginClientController {

    @Autowired
    private ClientService clientService;

    private GoogleUtils googleUtils;

    @GetMapping("/home")
    public String homeClient(Model model) {
        model.addAttribute("viewClient", "/WEB-INF/view/home/client.jsp");
        return "layout/client";
    }

    @GetMapping("/login-google")
    public String loginGoogle(@RequestParam("code") String code,
                              HttpSession session,
                              Model model) throws IOException {

        String gmail = "";
        if (code == null || code.isEmpty()) {
            return "login";
        } else {

            String accessToken = googleUtils.getToken(code);
            GooglePojo googlePojo = googleUtils.getUserInfo(accessToken);
            gmail = googlePojo.getEmail();

            Client client = clientService.detailGmail(googlePojo.getEmail());
            model.addAttribute("clientSession", client);
            session.setAttribute("clientSession", client);

        }
        model.addAttribute("viewClient", "/WEB-INF/view/home/client.jsp");
        return clientService.login(gmail);
    }

    @GetMapping("/sign-in")
    public String signIn(@RequestParam("gmail") String gmail,
                         @RequestParam("password") String password,
                         Model model,
                         HttpSession session){

        Client client = clientService.detailGmail(gmail);

        if(gmail.isEmpty() || password.isEmpty()){
            model.addAttribute("errors", "email hoặc password trống!");
            return "login/client";
        }
        if(!(gmail.equalsIgnoreCase(client.getGmail())) || !(password.equalsIgnoreCase(client.getPassword()))){
            model.addAttribute("errors", "email hoặc password bạn nhập sai!");
        }
        model.addAttribute("clientSession", client);
        session.setAttribute("clientSession", client);
        model.addAttribute("viewClient", "/WEB-INF/view/home/client.jsp");
        return clientService.SignIn(gmail, password);
    }

}

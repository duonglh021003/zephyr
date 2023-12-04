package com.example.demo.controller;

import com.example.demo.entity.Client;
import com.example.demo.entity.Favourite;
import com.example.demo.entity.Rank;
import com.example.demo.entity.ShoppingCart;
import com.example.demo.repository.FavouriteRepository;
import com.example.demo.repository.RankRepository;
import com.example.demo.service.ClientService;
import com.example.demo.service.FavouriteService;
import com.example.demo.service.ShoppingCartService;
import com.example.demo.validation.Until;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Random;

@Controller
@RequestMapping(value = "/zephyr/sing-up")
public class SingUpController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private RankRepository rankRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private FavouriteService favouriteService;


    LocalDate localDate = LocalDate.now();

    private String incrementCode(String code) {
        String prefix = code.substring(0, 2);
        int number = Integer.parseInt(code.substring(2));
        number++;
        String nextCode= String.format("%s%05d", prefix, number);
        return nextCode;
    }

    private String getNextCodeClient() {
        String currentCodeClient = clientService.findMaxCodeClient();
        String nextCodeClient = incrementCode(currentCodeClient);
        return nextCodeClient;
    }

    private String getNextCodeShoppingCart() {
        String currentCodeShoppingCart = shoppingCartService.findMaxCodeShoppingCart();
        String nextCodeShoppingCart = incrementCode(currentCodeShoppingCart);
        return nextCodeShoppingCart;
    }

    private String getNextCodeFavourite() {
        String currentCodeFavourite = shoppingCartService.findMaxCodeShoppingCart();
        String nextCodeFavourite = incrementCode(currentCodeFavourite);
        return nextCodeFavourite;
    }

    @PostMapping()
    public String add(@RequestParam("name") String name,
                      @RequestParam("gmail") String gmail,
                      @RequestParam("password") String password,
                      Model model) {

        String PASSWORD = "^(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

        for (Client clientErrors : clientService.findAll()){
            if(name.isEmpty()){
                model.addAttribute("errors", "name không được để trống!");
                return "login/client";
            }
            if(gmail.isEmpty()){
                model.addAttribute("errors", "email không được để trống!");
                return "login/client";
            }
            if(password.isEmpty()){
                model.addAttribute("errors", "password không được để trống!");
                return "login/client";
            }
            if(!(gmail.matches(Until.GMAIL))){
                model.addAttribute("errors", "email không đúng định dạng!");
                return "login/client";
            }
            if(gmail.equalsIgnoreCase(clientErrors.getGmail())){
                model.addAttribute("errors", "email đã tồn tại!");
                return "login/client";
            }
            if(!(password.matches(PASSWORD))){
                model.addAttribute("errors", "Chữ cái đầu phải viết hoa, length password phải lớn hơn 8, phải có ký tự đặc biệt");
                return "login/client";
            }
        }

        ShoppingCart shoppingCart = ShoppingCart.builder()
                .code(getNextCodeShoppingCart())
                .totalShoppingCart(0.0)
                .status(1)
                .build();
        shoppingCartService.add(shoppingCart);

        Favourite favourite = Favourite.builder()
                .code(getNextCodeFavourite())
                .status(1)
                .build();
        favouriteService.add(favourite);

        Client client = Client.builder()
                .code(getNextCodeClient())
                .name(name)
                .gmail(gmail)
                .pointUsr(0.0)
                .accumulatedScore(0.0)
                .dateCreate(localDate)
                .dateUpdate(localDate)
                .userCreate(name)
                .userUpdate(name)
                .password(password)
                .status(1)
                .rank(rankRepository.getById(1L))
                .shoppingCart(shoppingCart)
                .favourite(favourite)
                .build();
        clientService.add(client);

        model.addAttribute("errorsPass", "đăng ký thành công");

        return "login/client";
    }


}

package com.example.demo.controller;

import com.example.demo.entity.Client;
import com.example.demo.entity.Favourite;
import com.example.demo.entity.Rank;
import com.example.demo.entity.ShoppingCart;
import com.example.demo.repository.FavouriteRepository;
import com.example.demo.repository.RankRepository;
import com.example.demo.service.ClientService;
import com.example.demo.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    private FavouriteRepository favouriteRepository;


    LocalDate localDate = LocalDate.now();
    private static final Random random = new Random();

    public static String generateRandomStringClient() {
        StringBuilder sb = new StringBuilder(10);
        sb.append("CE");
        for (int i = 0; i < 5; i++) {
            int rndNum = random.nextInt(10);
            sb.append(rndNum);
        }
        return sb.toString();
    }

    public static String generateRandomStringShoppingCart() {
        StringBuilder sb = new StringBuilder(10);
        sb.append("SC");
        for (int i = 0; i < 5; i++) {
            int rndNum = random.nextInt(10);
            sb.append(rndNum);
        }
        return sb.toString();
    }

    public static String generateRandomStringFavourite() {
        StringBuilder sb = new StringBuilder(10);
        sb.append("FR");
        for (int i = 0; i < 5; i++) {
            int rndNum = random.nextInt(10);
            sb.append(rndNum);
        }
        return sb.toString();
    }

    @PostMapping()
    public String add(@RequestParam("name") String name,
                      @RequestParam("gmail") String gmail,
                      @RequestParam("password") String password) {

        System.out.println("aaaaaaaaaaaaaaaaaa          " + name);
        System.out.println("bbbbbbbbbbbbbbbbbb          " + gmail);
        System.out.println("cccccccccccccccccc          " + password);

        Client client = Client.builder()
                .code(generateRandomStringClient())
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
                .build();
        clientService.add(client);

        Client clientUpdate = clientService.detailGmail(gmail);

        ShoppingCart shoppingCart = ShoppingCart.builder()
                .code(generateRandomStringShoppingCart())
                .totalShoppingCart(0.0)
                .status(1)
                .build();
        shoppingCartService.add(shoppingCart);

        Favourite favourite = Favourite.builder()
                .code(generateRandomStringFavourite())
                .status(1)
                .build();
        favouriteRepository.save(favourite);

        System.out.println("aaaaaaaaaaaaaaaaaa      "+shoppingCart);
        System.out.println("bbbbbbbbbbbbbbbbbb      "+favourite);
        clientUpdate.setShoppingCart(shoppingCart);
        clientUpdate.setFavourite(favourite);
        clientService.update(clientUpdate, clientUpdate.getId());


        return "redirect:/zephyr/login";
    }


}

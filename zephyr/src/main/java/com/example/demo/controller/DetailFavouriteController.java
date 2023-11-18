package com.example.demo.controller;

import com.example.demo.entity.Client;
import com.example.demo.entity.DetailsFavourite;
import com.example.demo.entity.ProductDetails;
import com.example.demo.service.DetailFavouriteService;
import com.example.demo.service.ProductDetailsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(value = "/zephyr/favourite-detail")
public class DetailFavouriteController {

    @Autowired
    private DetailFavouriteService detailFavouriteService;

    @Autowired
    private ProductDetailsService productDetailsService;

    @GetMapping()
    public String index(Model model, HttpSession session){

        Long idFavourite;
        Client client = (Client) session.getAttribute("clientSession");
        if(String.valueOf(client).equalsIgnoreCase("null")){
            return "redirect:/zephyr/login";
        }
        idFavourite = client.getFavourite().getId();
        List<DetailsFavourite> list = detailFavouriteService.findAllById(idFavourite);
        System.out.println("bbbbbbbbbbbbbbbbbbb     "+idFavourite);
        System.out.println("ccccccccccccccccccc     "+list);
        model.addAttribute("listFavouriteDetail", list);
        model.addAttribute("viewClient", "/WEB-INF/view/include/favourite-details.jsp");
        return "layout/client";
    }

    @GetMapping("/add")
    public String add(@RequestParam("id") Long id,
                      Model model, HttpSession session){

        LocalDate localDate = LocalDate.now();
        ProductDetails productDetails = productDetailsService.detail(id);
        Client client = (Client) session.getAttribute("clientSession");
        if(String.valueOf(client).equalsIgnoreCase("null")){
            return "redirect:/zephyr/login";
        }

        DetailsFavourite detailsFavourite = DetailsFavourite.builder()
                .dateCreate(localDate)
                .status(productDetails.getStatus())
                .favourite(client.getFavourite())
                .productDetails(productDetails)
                .build();

        detailFavouriteService.add(detailsFavourite);
        return "redirect:/zephyr/shop";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id){

        detailFavouriteService.delete(id);
        return "redirect:/zephyr/favourite-detail";
    }
}

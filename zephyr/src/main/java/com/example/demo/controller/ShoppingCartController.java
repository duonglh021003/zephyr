package com.example.demo.controller;

import com.example.demo.entity.Address;
import com.example.demo.entity.Client;
import com.example.demo.entity.DetailedShoppingCart;
import com.example.demo.entity.ShoppingCart;
import com.example.demo.entity.Staff;
import com.example.demo.service.ClientService;
import com.example.demo.service.DetailedShoppingCartService;
import com.example.demo.service.ShoppingCartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/zephyr")
public class ShoppingCartController {

    @Autowired
    private DetailedShoppingCartService detailedShoppingCartService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/shopping-cart")
    public String shoppingCart(
            Model model,
            HttpSession session) {
        Long idShopping;
        Double total = 0.00;

        Client client = (Client) session.getAttribute("clientSession");
        idShopping= client.getShoppingCart().getId();
        List<DetailedShoppingCart> list = detailedShoppingCartService.findAllById(idShopping);
        for(DetailedShoppingCart cart : list){
            total += cart.subTotal();
        }
        int listSize = list.size();
        model.addAttribute("listSize", listSize);
        session.setAttribute("listSize", listSize);
        model.addAttribute("totalShoppingCart",total);
        model.addAttribute("listDetailShoppingCart",list);
        model.addAttribute("listSubTotal",list);

        model.addAttribute("viewClient", "/WEB-INF/view/include/shopping-cart.jsp");
        return "layout/client";
    }

    @PostMapping("/shopping-cart/less")
    public String less(@RequestParam("id") Long id,
                       Model model){

        Integer getQuantity = 0;
        DetailedShoppingCart detailedShoppingCart = detailedShoppingCartService.detail(id);
        getQuantity = detailedShoppingCart.getQuantity() - 1;
        detailedShoppingCart.setQuantity(getQuantity);
        detailedShoppingCartService.update(detailedShoppingCart, id);

        return "redirect:/zephyr/shopping-cart";
    }

    @PostMapping("/shopping-cart/plus")
    public String plus(@RequestParam("id") Long id,
                       Model model){

        Integer getQuantity = 0;
        DetailedShoppingCart detailedShoppingCart = detailedShoppingCartService.detail(id);
        getQuantity = detailedShoppingCart.getQuantity() + 1;
        detailedShoppingCart.setQuantity(getQuantity);
        detailedShoppingCartService.update(detailedShoppingCart, id);

        return "redirect:/zephyr/shopping-cart";
    }
}

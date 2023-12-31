package com.example.demo.controller;

import com.example.demo.entity.Client;
import com.example.demo.entity.DetailedShoppingCart;
import com.example.demo.entity.Invoice;
import com.example.demo.entity.ProductDetails;
import com.example.demo.service.ClientService;
import com.example.demo.service.DetailedShoppingCartService;
import com.example.demo.service.InvoiceService;
import com.example.demo.service.ProductDetailsService;
import com.example.demo.service.ShoppingCartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
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

    @Autowired
    private ProductDetailsService productDetailsService;

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/shopping-cart")
    public String shoppingCart(
            Model model,
            HttpSession session) {
        Long idShopping;
        Double total = 0.00;

        Client client = (Client) session.getAttribute("clientSession");
        if (String.valueOf(client).equalsIgnoreCase("null")) {
            return "redirect:/zephyr/shopping-cart-null";
        }
        idShopping = client.getShoppingCart().getId();
        List<DetailedShoppingCart> list = detailedShoppingCartService.findAllById(idShopping);
        for (DetailedShoppingCart cart : list) {
            total += cart.subTotal();
        }
        int listSize = list.size();
        model.addAttribute("listSize", listSize);
        session.setAttribute("listSize", listSize);
        model.addAttribute("totalShoppingCart", total);
        model.addAttribute("listDetailShoppingCart", list);
        model.addAttribute("listSubTotal", list);

        for (Invoice invoice02 : invoiceService.findByInvoiceStatus1(client.getId())) {
            invoiceService.delete(invoice02.getId());
        }

        model.addAttribute("viewClient", "/WEB-INF/view/include/shopping-cart.jsp");
        return "layout/client";
    }

    @PostMapping("/shopping-cart/less")
    public String less(@RequestParam("id") Long id,
                       Model model) {

        Integer getQuantity = 0;
        DetailedShoppingCart detailedShoppingCart = detailedShoppingCartService.detail(id);
        getQuantity = detailedShoppingCart.getQuantity() - 1;
        detailedShoppingCart.setQuantity(getQuantity);
        if (getQuantity == 0) {
            detailedShoppingCartService.delete(id);
            return "redirect:/zephyr/shopping-cart";
        }
        detailedShoppingCartService.update(detailedShoppingCart, id);
        return "redirect:/zephyr/shopping-cart";
    }

    @PostMapping("/shopping-cart/plus")
    public String plus(@RequestParam("id") Long id,
                       Model model,HttpSession session) {
        Integer getQuantity = 0;
        DetailedShoppingCart detailedShoppingCart = detailedShoppingCartService.detail(id);
        if(detailedShoppingCart.getQuantity() == 10){
            Long idShopping;
            Double total = 0.00;
            Client client = (Client) session.getAttribute("clientSession");
            if (String.valueOf(client).equalsIgnoreCase("null")) {
                return "redirect:/zephyr/shopping-cart-null";
            }
            idShopping = client.getShoppingCart().getId();
            List<DetailedShoppingCart> list = detailedShoppingCartService.findAllById(idShopping);
            for (DetailedShoppingCart cart : list) {
                total += cart.subTotal();
            }
            int listSize = list.size();
            model.addAttribute("listSize", listSize);
            session.setAttribute("listSize", listSize);
            model.addAttribute("totalShoppingCart", total);
            model.addAttribute("listDetailShoppingCart", list);
            model.addAttribute("listSubTotal", list);
            model.addAttribute("errorPlus", "số lượng mua phải nhỏ hơn hoặc bằng 10");
            model.addAttribute("viewClient", "/WEB-INF/view/include/shopping-cart.jsp");
            return "layout/client";
        }
        getQuantity = detailedShoppingCart.getQuantity() + 1;
        detailedShoppingCart.setQuantity(getQuantity);
        detailedShoppingCartService.update(detailedShoppingCart, id);


//        ProductDetails productDetails = productDetailsService.detail(detailedShoppingCart.getProductDetails().getId());
//        productDetails.setInventory(productDetails.getInventory() - 1);
//        productDetailsService.update(productDetails, productDetails.getId());

        return "redirect:/zephyr/shopping-cart";
    }

    @GetMapping("/shopping-cart/add")
    public String add(@RequestParam("id") Long id,
                      HttpSession session) {

        LocalDate localDate = LocalDate.now();
        DetailedShoppingCart iddetailedShoppingCart = detailedShoppingCartService.detail(id);
        ProductDetails idProductDetails = productDetailsService.detail(id);
        Client client = (Client) session.getAttribute("clientSession");
        if (String.valueOf(client).equalsIgnoreCase("null")) {
            return "redirect:/zephyr/login";
        }
        List<DetailedShoppingCart> list = detailedShoppingCartService.findByIdProduct(client.getShoppingCart(), idProductDetails);
        for (DetailedShoppingCart cart : list) {
            Integer getQuanti = cart.getQuantity() + 1;
            cart = DetailedShoppingCart.builder()
                    .id(cart.getId())
                    .quantity(getQuanti)
                    .unitPrice(idProductDetails.getPrice())
                    .dateCreate(localDate)
                    .status(1)
                    .shoppingCart(client.getShoppingCart())
                    .productDetails(idProductDetails)
                    .build();
//            Integer getInven = idProductDetails.getInventory() - 1;
//            idProductDetails.setInventory(getInven);
//            productDetailsService.update(idProductDetails, id);
            detailedShoppingCartService.update(cart, cart.getId());
            return "redirect:/zephyr/shop";
        }
        DetailedShoppingCart detailedShoppingCart = DetailedShoppingCart.builder()
                .quantity(1)
                .unitPrice(idProductDetails.getPrice())
                .dateCreate(localDate)
                .status(1)
                .shoppingCart(client.getShoppingCart())
                .productDetails(idProductDetails)
                .build();

//        Integer getInventory = idProductDetails.getInventory() - 1;
//        idProductDetails.setInventory(getInventory);
//        productDetailsService.update(idProductDetails, id);
        detailedShoppingCartService.add(detailedShoppingCart);
        return "redirect:/zephyr/shop";
    }

    @GetMapping("/shopping-cart/delete")
    public String delete(@RequestParam("id") Long id) {

//        DetailedShoppingCart detailedShoppingCart = detailedShoppingCartService.detail(id);
//        System.out.println("aaaaaaaaaaaa    "+detailedShoppingCart);
//        ProductDetails productDetails = productDetailsService.detail(detailedShoppingCart.getProductDetails().getId());
//        productDetails.setInventory(productDetails.getInventory() + detailedShoppingCart.getQuantity());
//        productDetailsService.update(productDetails, productDetails.getId());

        detailedShoppingCartService.delete(id);
        return "redirect:/zephyr/shopping-cart";
    }


}

package com.example.demo.controller;

import com.example.demo.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/zephyr/admin/statistical")
public class StatisticalController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/index")
    public String detail(Model model){

        List<Integer> listDay = invoiceService.findAllStatisticalProductDay();
        Integer productDay = listDay.get(0);
        List<Double> listDayPresent = invoiceService.findAllStatisticalIntoMoneyDAYPresent();
        Double intoMoneyDayPresent = listDayPresent.get(0);

        List<Double> listMonthPresent = invoiceService.findAllStatisticalIntoMoneyMONTHPresent();
        Double intoMoneyMonthPresent = listMonthPresent.get(0);
        List<Integer> listQuantityMonthPresent = invoiceService.findAllStatisticalQuantityMONTHPresent();
        Integer quantityMonthPresent = listQuantityMonthPresent.get(0);

        List<Double> listYearPresent = invoiceService.findAllStatisticalIntoMoneyYEARPresent();
        Double intoMoneyYearPresent = listYearPresent.get(0);
        List<Integer> listQuantityYearPresent = invoiceService.findAllStatisticalQuantityYEARPresent();
        Integer quantityYearPresent = listQuantityYearPresent.get(0);

        model.addAttribute("productDay", productDay);
        model.addAttribute("IntoMoneyDayPresent", intoMoneyDayPresent);

        model.addAttribute("IntoMoneyMonthPresent", intoMoneyMonthPresent);
        model.addAttribute("quantityMonthPresent", quantityMonthPresent);

        model.addAttribute("IntoMoneyYearPresent", intoMoneyYearPresent);
        model.addAttribute("quantityYearPresent", quantityYearPresent);

        model.addAttribute("view", "/WEB-INF/view/statistical/index.jsp");
        return "home/staff";
    }


}

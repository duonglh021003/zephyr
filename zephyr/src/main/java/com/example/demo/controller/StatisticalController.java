package com.example.demo.controller;

import com.example.demo.service.DetailedInvoiceService;
import com.example.demo.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/zephyr/admin/statistical")
public class StatisticalController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private DetailedInvoiceService detailedInvoiceService;

    @GetMapping("/index")
    public String index(Model model){

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

        model.addAttribute("listInvoice", invoiceService.findAllStatisticalInvoiceProductDay());
        model.addAttribute("view", "/WEB-INF/view/statistical/index.jsp");
        return "home/staff";
    }


    @GetMapping("/detail")
    public String detail(@RequestParam("id") Long id, Model model){

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

        model.addAttribute("listInvoice", invoiceService.findAllStatisticalInvoiceProductDay());
        model.addAttribute("listInvoiceDetail", detailedInvoiceService.findAllByIdInvoice(id));
        model.addAttribute("view", "/WEB-INF/view/statistical/index.jsp");
        return "home/staff";
    }

    @GetMapping("/revenue-in-month")
    public String month(Model model){

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

        model.addAttribute("listInvoice", invoiceService.findAllStatisticalInvoiceMonth());
        model.addAttribute("view", "/WEB-INF/view/statistical/month.jsp");
        return "home/staff";
    }

    @GetMapping("/revenue-in-month/detail")
    public String detailMonth(@RequestParam("id") Long id, Model model){

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

        model.addAttribute("listInvoice", invoiceService.findAllStatisticalInvoiceMonth());
        model.addAttribute("listInvoiceDetail", detailedInvoiceService.findAllByIdInvoice(id));
        model.addAttribute("view", "/WEB-INF/view/statistical/month.jsp");
        return "home/staff";
    }

    @GetMapping("/revenue-in-month/search")
    public String searchMonth(@RequestParam("month") Integer month,
                              @RequestParam("year") Integer year,
                             Model model){

        List<Integer> listDay = invoiceService.findAllStatisticalProductDay();
        Integer productDay = listDay.get(0);
        List<Double> listDayPresent = invoiceService.findAllStatisticalIntoMoneyDAYPresent();
        Double intoMoneyDayPresent = listDayPresent.get(0);

        List<Double> listMonthPresent = invoiceService.findAllStatisticalSearchMonth(month, year);
        Double intoMoneyMonthPresent = listMonthPresent.get(0);
        List<Integer> listQuantityMonthPresent = invoiceService.findAllStatisticalQuantitySearchMonth(month, year);
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

        model.addAttribute("listInvoice", invoiceService.findAllStatisticalInvoiceSearchMonth(month, year));

        model.addAttribute("view", "/WEB-INF/view/statistical/month.jsp");
        return "home/staff";
    }

    @GetMapping("/revenue-in-year")
    public String year(Model model){

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

        model.addAttribute("listInvoice", invoiceService.findAllStatisticalInvoiceYear());
        model.addAttribute("view", "/WEB-INF/view/statistical/year.jsp");
        return "home/staff";
    }

    @GetMapping("/revenue-in-year/detail")
    public String detailYear(@RequestParam("id") Long id, Model model){

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

        model.addAttribute("listInvoice", invoiceService.findAllStatisticalInvoiceYear());
        model.addAttribute("listInvoiceDetail", detailedInvoiceService.findAllByIdInvoice(id));
        model.addAttribute("view", "/WEB-INF/view/statistical/year.jsp");
        return "home/staff";
    }

    @GetMapping("/revenue-in-year/search")
    public String searchYear(@RequestParam("year") Integer year,
                             Model model){

        List<Integer> listDay = invoiceService.findAllStatisticalProductDay();
        Integer productDay = listDay.get(0);
        List<Double> listDayPresent = invoiceService.findAllStatisticalIntoMoneyDAYPresent();
        Double intoMoneyDayPresent = listDayPresent.get(0);

        List<Double> listMonthPresent = invoiceService.findAllStatisticalIntoMoneyMONTHPresent();
        Double intoMoneyMonthPresent = listMonthPresent.get(0);
        List<Integer> listQuantityMonthPresent = invoiceService.findAllStatisticalQuantityMONTHPresent();
        Integer quantityMonthPresent = listQuantityMonthPresent.get(0);

        List<Double> listSearchYear = invoiceService.findAllStatisticalSearchYear(year);
        Double intoMoneyYearPresent = listSearchYear.get(0);
        List<Integer> listQuantityYearSearch = invoiceService.findAllStatisticalQuantitySearchYear(year);
        Integer quantityYearPresent = listQuantityYearSearch.get(0);

        model.addAttribute("productDay", productDay);
        model.addAttribute("IntoMoneyDayPresent", intoMoneyDayPresent);

        model.addAttribute("IntoMoneyMonthPresent", intoMoneyMonthPresent);
        model.addAttribute("quantityMonthPresent", quantityMonthPresent);

        model.addAttribute("IntoMoneyYearPresent", intoMoneyYearPresent);
        model.addAttribute("quantityYearPresent", quantityYearPresent);

        model.addAttribute("listInvoice", invoiceService.findAllStatisticalInvoiceSearchYear(year));

        model.addAttribute("view", "/WEB-INF/view/statistical/year.jsp");
        return "home/staff";
    }


}

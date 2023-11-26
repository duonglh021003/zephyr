package com.example.demo.controller;

import com.example.demo.entity.Color;
import com.example.demo.entity.Size;
import com.example.demo.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/zephyr/admin/color")
public class ColorController {

    @Autowired
    private ColorService colorService;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "0", name = "page") Integer number,
                        Model model){

        Pageable pageable = PageRequest.of(number, 10);
        Page<Color> pageColor = colorService.findAllByStatus1(pageable);
        model.addAttribute("listColor", pageColor);

        model.addAttribute("view", "/WEB-INF/view/color/index.jsp");
        return "home/staff";
    }

}

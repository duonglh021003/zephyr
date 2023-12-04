package com.example.demo.controller;

import com.example.demo.entity.Color;
import com.example.demo.entity.Size;
import com.example.demo.service.ColorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping(value = "/zephyr/admin/color")
public class ColorController {

    @Autowired
    private ColorService colorService;

    private String incrementCodeOrder(String code) {
        String prefix = code.substring(0, 2);
        int number = Integer.parseInt(code.substring(2));
        number++;
        String nextCode = String.format("%s%05d", prefix, number);
        return nextCode;
    }

    private String getNextCode() {
        String currentCode = colorService.findMaxCodeColor();
        String nextCode = incrementCodeOrder(currentCode);
        return nextCode;
    }

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "0", name = "page") Integer number,
                        Model model) {

        Pageable pageable = PageRequest.of(number, 10);
        Page<Color> pageColor = colorService.findAllByStatus1(pageable);
        model.addAttribute("listColor", pageColor);
        model.addAttribute("listRestore", colorService.findAllByStatus0());

        model.addAttribute("view", "/WEB-INF/view/color/index.jsp");
        return "home/staff";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        LocalDate localDate = LocalDate.now();
        model.addAttribute("dateUpdate", localDate);
        model.addAttribute("color", new Color());
        model.addAttribute("view", "/WEB-INF/view/color/view-add.jsp");
        return "home/staff";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("color") Color color,
                      BindingResult result,
                      @RequestParam("name") String name,
                      @RequestParam("dateCreate") LocalDate dateCreate,
                      @RequestParam("dateUpdate") LocalDate dateUpdate,
                      @RequestParam("userCreate") String userCreate,
                      @RequestParam("userUpdate") String userUpdate,
                      @RequestParam("status") Integer status,

                      Model model) {

        if (result.hasErrors()) {
            LocalDate localDate = LocalDate.now();
            model.addAttribute("dateUpdate", localDate);
            model.addAttribute("view", "/WEB-INF/view/color/view-add.jsp");
            return "home/staff";
        }

        color = Color.builder()
                .code(getNextCode())
                .name(name)
                .dateCreate(dateCreate)
                .dateUpdate(dateUpdate)
                .userCreate(userCreate)
                .userUpdate(userUpdate)
                .status(status)
                .build();
        colorService.add(color);

        return "redirect:/zephyr/admin/color/index";
    }

    @GetMapping("/view-update")
    public String viewUpdate(@RequestParam("id") Long id, Model model) {

        LocalDate localDate = LocalDate.now();
        Color color = colorService.detail(id);
        model.addAttribute("color", color);
        model.addAttribute("dateUpdate", localDate);

        model.addAttribute("view", "/WEB-INF/view/color/view-update.jsp");
        return "home/staff";
    }

    @PostMapping("update")
    public String update(@Valid @ModelAttribute("color") Color color,
                         BindingResult result,
                         Model model) {

        if (result.hasErrors()) {
            LocalDate localDate = LocalDate.now();
            model.addAttribute("dateUpdate", localDate);
            model.addAttribute("view", "/WEB-INF/view/color/view-update.jsp");
            return "home/staff";
        }

        colorService.update(color);
        return "redirect:/zephyr/admin/color/index";
    }

    @GetMapping("/restore")
    public String restore(@RequestParam("id") Long id) {

        Color color = colorService.detail(id);
        color.setStatus(1);
        colorService.update(color);
        return "redirect:/zephyr/admin/color/index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {

        Color color = colorService.detail(id);
        color.setStatus(0);
        colorService.update(color);
        return "redirect:/zephyr/admin/color/index";
    }

    @GetMapping("/search")
    public String search(@RequestParam("inputColor") String inputColor,
                         @RequestParam(defaultValue = "0", name = "page") Integer number,
                         Model model) {
        Pageable pageable = PageRequest.of(number, 10);
        Page<Color> pageColor = colorService.findAllByColorSearch(inputColor, pageable);
        model.addAttribute("listColor", pageColor);
        model.addAttribute("listRestore", colorService.findAllByStatus0());

        model.addAttribute("view", "/WEB-INF/view/color/index.jsp");
        return "home/staff";
    }
}

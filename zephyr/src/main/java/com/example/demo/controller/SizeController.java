package com.example.demo.controller;

import com.example.demo.entity.Origin;
import com.example.demo.entity.Product;
import com.example.demo.entity.Size;
import com.example.demo.service.SizeService;
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
@RequestMapping(value = "/zephyr/admin/size")
public class SizeController {

    @Autowired
    private SizeService sizeService;

    private String incrementCodeOrder(String code) {
        String prefix = code.substring(0, 2);
        int number = Integer.parseInt(code.substring(2));
        number++;
        String nextCode = String.format("%s%05d", prefix, number);
        return nextCode;
    }

    private String getNextCode() {
        String currentCode = sizeService.findMaxCodeSize();
        String nextCode = incrementCodeOrder(currentCode);
        return nextCode;
    }

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "0", name = "page") Integer number,
                        Model model) {

        Pageable pageable = PageRequest.of(number, 10);
        Page<Size> pageSize = sizeService.findAllByStatus1(pageable);
        model.addAttribute("listSize", pageSize);
        model.addAttribute("listRestore", sizeService.findAllByStatus0());

        model.addAttribute("view", "/WEB-INF/view/size/index.jsp");
        return "home/staff";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        LocalDate localDate = LocalDate.now();
        model.addAttribute("dateUpdate", localDate);
        model.addAttribute("size", new Size());
        model.addAttribute("view", "/WEB-INF/view/size/view-add.jsp");
        return "home/staff";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("size") Size size,
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
            model.addAttribute("view", "/WEB-INF/view/size/view-add.jsp");
            return "home/staff";
        }

        size = Size.builder()
                .code(getNextCode())
                .name(name)
                .dateCreate(dateCreate)
                .dateUpdate(dateUpdate)
                .userCreate(userCreate)
                .userUpdate(userUpdate)
                .status(status)
                .build();
        sizeService.add(size);

        return "redirect:/zephyr/admin/size/index";
    }

    @GetMapping("/view-update")
    public String viewUpdate(@RequestParam("id") Long id, Model model) {

        LocalDate localDate = LocalDate.now();
        Size size = sizeService.detail(id);
        model.addAttribute("size", size);
        model.addAttribute("dateUpdate", localDate);

        model.addAttribute("view", "/WEB-INF/view/size/view-update.jsp");
        return "home/staff";
    }

    @PostMapping("update")
    public String update(@Valid @ModelAttribute("size") Size size,
                         BindingResult result,
                         Model model) {

        if (result.hasErrors()) {
            LocalDate localDate = LocalDate.now();
            model.addAttribute("dateUpdate", localDate);
            model.addAttribute("view", "/WEB-INF/view/size/view-add.jsp");
            return "home/staff";
        }

        sizeService.update(size, size.getId());
        return "redirect:/zephyr/admin/size/index";
    }

    @GetMapping("/restore")
    public String restore(@RequestParam("id") Long id) {

        Size size = sizeService.detail(id);
        size.setStatus(1);
        sizeService.update(size, size.getId());
        return "redirect:/zephyr/admin/size/index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {

        Size size = sizeService.detail(id);
        size.setStatus(0);
        sizeService.update(size, size.getId());
        return "redirect:/zephyr/admin/size/index";
    }

    @GetMapping("/search")
    public String search(@RequestParam("inputSize") String inputSize,
                         @RequestParam(defaultValue = "0", name = "page") Integer number,
                         Model model) {
        Pageable pageable = PageRequest.of(number, 10);
        Page<Size> pageSize = sizeService.findAllBySizeSearch(inputSize, pageable);
        model.addAttribute("listSize", pageSize);
        model.addAttribute("listRestore", sizeService.findAllByStatus0());

        model.addAttribute("view", "/WEB-INF/view/size/index.jsp");
        return "home/staff";
    }

}

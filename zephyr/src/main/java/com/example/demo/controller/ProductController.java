package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@SessionAttributes({"phoneNumber", "password"})
public class ProductController {
    private final long date = System.currentTimeMillis();
    private final Date dateNow = new Date(date);
    private static final String CHAR_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUM = "0123456789";
    private static final String DATA_FOR_RANDOM_STRING = CHAR_UPPER + NUM;
    private static final Random random = new Random();

    List<Product> listProduct = new ArrayList<>();
    List<Color> listColor = new ArrayList<>();
    List<Origin> listOrigin = new ArrayList<>();
    List<Size> listSize = new ArrayList<>();

    @Autowired
    ProductService service;

    @ModelAttribute("phoneNumber")
    public String getPhoneNumber(Model model) {
        return (String) model.getAttribute("phoneNumber");
    }

    @ModelAttribute("password")
    public String getPassword(Model model) {
        return (String) model.getAttribute("password");
    }

    // Kiểm tra Đã Đăng Nhập Chưa
    public String login(String phoneNumber, String password, Model model) {
        Staff staff = service.detailStaffByPhoneNumberAndPassword(phoneNumber, password);
        if (staff == null) {
            return "login/staff";
        } else if (phoneNumber.equalsIgnoreCase(staff.getPhoneNumber()) && password.equalsIgnoreCase(staff.getPassword())) {
            // Update the session attributes with the login information
            model.addAttribute("phoneNumber", phoneNumber);
            model.addAttribute("password", password);
            return "redirect:/zephyr/staff/home-page";
        } else {
            return "login/staff";
        }
    }

    // Khởi tạo chuỗi kí tự cả chữ cái in hoa và số
    public static String generateRandomString() {
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);
            sb.append(rndChar);
        }
        return sb.toString();
    }

    // CONTROLLER PRODUCT
    @RequestMapping(value = "/zephyr/product", method = RequestMethod.GET)
    public String viewProduct(Model model,
                              @RequestParam(defaultValue = "0") int page,
                              @ModelAttribute("phoneNumber") String phoneNumber,
                              @ModelAttribute("password") String password) {
        Staff detailStaff = service.detailStaffByPhoneNumberAndPassword(phoneNumber, password);
        model.addAttribute("detailStaff", detailStaff);
        Page<Product> itemsProduct = service.getAllProducts(PageRequest.of(page, 5));
        model.addAttribute("listProduct", itemsProduct);
        model.addAttribute("currentPage", page);
        model.addAttribute("main", "/WEB-INF/view/detail/product/listProduct.jsp");
        return "home_page/staff";
    }

    @RequestMapping(value = "/zephyr/product/view-add", method = RequestMethod.GET)
    public String viewAddProduct(Model model,
                                 @ModelAttribute("phoneNumber") String phoneNumber,
                                 @ModelAttribute("password") String password) {
        Staff detailStaff = service.detailStaffByPhoneNumberAndPassword(phoneNumber, password);
        model.addAttribute("detailStaff", detailStaff);
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("main", "/WEB-INF/view/detail/product/view-add.jsp");
        return "home_page/staff";
    }

    @RequestMapping(value = "/zephyr/product/add", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("product") Product product,
                             @ModelAttribute("phoneNumber") String phoneNumber,
                             @ModelAttribute("password") String password) {
        System.out.println("ddddddddddddddddđ : " + phoneNumber);
        System.out.println("bgggggggggggggggggggg : " + password);
        Staff detailStaff = service.detailStaffByPhoneNumberAndPassword(phoneNumber, password);
        System.out.println("tttttttttttttttt : " + detailStaff);
        Product newProduct1 = Product.builder()
                .codeProduct(generateRandomString())
                .nameProduct(product.getNameProduct())
                .dateCreate(LocalDate.now())
                .dateUpdate(LocalDate.now())
                .userCreate(detailStaff.getName())
                .userUpdate(detailStaff.getName())
                .status(1)
                .build();
        service.addProduct(newProduct1);
        return "redirect:/zephyr/product";
    }

    @RequestMapping(value = "/zephyr/product/detail/{idProduct}", method = RequestMethod.GET)
    public String detailProduct(Model model,
                                @PathVariable("idProduct") Long idProduct,
                                @ModelAttribute("phoneNumber") String phoneNumber,
                                @ModelAttribute("password") String password) {
        Product detailProduct = service.detailProduct(idProduct);
        Staff detailStaff = service.detailStaffByPhoneNumberAndPassword(phoneNumber, password);
        model.addAttribute("detailStaff", detailStaff);
        model.addAttribute("detailProduct", detailProduct);
        model.addAttribute("main", "/WEB-INF/view/detail/product/view-update.jsp");
        return "home_page/staff";
    }

    @RequestMapping(value = "/zephyr/product/update", method = RequestMethod.POST)
    public String updateProduct(@ModelAttribute("detailProduct") Product product,
                                @ModelAttribute("phoneNumber") String phoneNumber,
                                @ModelAttribute("password") String password) {
        System.out.println("ddddddddddddddddđ : " + phoneNumber);
        System.out.println("bgggggggggggggggggggg : " + password);
        Staff detailStaff = service.detailStaffByPhoneNumberAndPassword(phoneNumber, password);
        System.out.println("tttttttttttttttt : " + detailStaff);
        Product detailProduct1 = service.detailProduct(product.getIdProduct());
        System.out.println("eeeeeeeeeeeeeeeeeeee : " + product.getIdProduct());
        System.out.println("iiiiiiiiiiiiiiiiiiiii : " + detailProduct1);
        Product newProduct1 = Product.builder()
                .idProduct(detailProduct1.getIdProduct())
                .codeProduct(detailProduct1.getCodeProduct())
                .nameProduct(product.getNameProduct())
                .dateCreate(detailProduct1.getDateCreate())
                .dateUpdate(LocalDate.now())
                .userCreate(detailStaff.getName())
                .userUpdate(detailStaff.getName())
                .status(product.getStatus())
                .build();
        System.out.println("zzzzzzzzzzzzzzzzzzzzz : " + newProduct1);
        service.addProduct(newProduct1);
        return "redirect:/zephyr/product";
    }

    @RequestMapping(value = "/zephyr/product/delete/{idProduct}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable("idProduct") Long idProduct) {
        service.deleteProduct(idProduct);
        return "redirect:/zephyr/product";
    }

    // CONTROLLER COLOR
    @RequestMapping(value = "/zephyr/color", method = RequestMethod.GET)
    public String viewColor(Model model,
                            @RequestParam(defaultValue = "0") int page,
                            @ModelAttribute("phoneNumber") String phoneNumber,
                            @ModelAttribute("password") String password) {
        Staff detailStaff = service.detailStaffByPhoneNumberAndPassword(phoneNumber, password);
        model.addAttribute("detailStaff", detailStaff);
        Page<Color> itemsColor = service.getAllColors(PageRequest.of(page, 5));
        model.addAttribute("listColor", itemsColor);
        model.addAttribute("currentPage", page);
        model.addAttribute("main", "/WEB-INF/view/detail/color/listColor.jsp");
        return "home_page/staff";
    }

    @RequestMapping(value = "/zephyr/color/view-add", method = RequestMethod.GET)
    public String viewAddColor(Model model,
                               @ModelAttribute("phoneNumber") String phoneNumber,
                               @ModelAttribute("password") String password) {
        Color color = new Color();
        Staff detailStaff = service.detailStaffByPhoneNumberAndPassword(phoneNumber, password);
        model.addAttribute("detailStaff", detailStaff);
        model.addAttribute("color", color);
        model.addAttribute("main", "/WEB-INF/view/detail/color/view-add.jsp");
        return "home_page/staff";
    }

    @RequestMapping(value = "/zephyr/color/add", method = RequestMethod.POST)
    public String addColor(@ModelAttribute("color") Color color,
                           @ModelAttribute("phoneNumber") String phoneNumber,
                           @ModelAttribute("password") String password) {
        System.out.println("ddddddddddddddddđ : " + phoneNumber);
        System.out.println("bgggggggggggggggggggg : " + password);
        Staff detailStaff = service.detailStaffByPhoneNumberAndPassword(phoneNumber, password);
        System.out.println("tttttttttttttttt : " + detailStaff);
        Color newColor1 = Color.builder()
                .codeColor(generateRandomString())
                .nameColor(color.getNameColor())
                .dateCreate(LocalDate.now())
                .dateUpdate(LocalDate.now())
                .userCreate(detailStaff.getName())
                .userUpdate(detailStaff.getName())
                .status(1)
                .build();
        service.addColor(newColor1);
        return "redirect:/zephyr/color";
    }

    @RequestMapping(value = "/zephyr/color/detail/{idColor}", method = RequestMethod.GET)
    public String detailColor(Model model,
                              @PathVariable("idColor") Long idColor,
                              @ModelAttribute("phoneNumber") String phoneNumber,
                              @ModelAttribute("password") String password) {
        Color detailColor = service.detailColor(idColor);
        Staff detailStaff = service.detailStaffByPhoneNumberAndPassword(phoneNumber, password);
        model.addAttribute("detailStaff", detailStaff);
        model.addAttribute("detailColor", detailColor);
        model.addAttribute("main", "/WEB-INF/view/detail/color/view-update.jsp");
        return "home_page/staff";
    }

    @RequestMapping(value = "/zephyr/color/update", method = RequestMethod.POST)
    public String updateProduct(@ModelAttribute("detailColor") Color color,
                                @ModelAttribute("phoneNumber") String phoneNumber,
                                @ModelAttribute("password") String password) {
        System.out.println("ddddddddddddddddđ : " + phoneNumber);
        System.out.println("bgggggggggggggggggggg : " + password);
        Staff detailStaff = service.detailStaffByPhoneNumberAndPassword(phoneNumber, password);
        System.out.println("tttttttttttttttt : " + detailStaff);
        Color detailColor1 = service.detailColor(color.getIdColor());
        System.out.println("eeeeeeeeeeeeeeeeeeee : " + color.getIdColor());
        System.out.println("iiiiiiiiiiiiiiiiiiiii : " + detailColor1);
        Color newColor1 = Color.builder()
                .idColor(detailColor1.getIdColor())
                .codeColor(detailColor1.getCodeColor())
                .nameColor(color.getNameColor())
                .dateCreate(detailColor1.getDateCreate())
                .dateUpdate(LocalDate.now())
                .userCreate(detailStaff.getName())
                .userUpdate(detailStaff.getName())
                .status(color.getStatus())
                .build();
        System.out.println("zzzzzzzzzzzzzzzzzzzzz : " + newColor1);
        service.addColor(newColor1);
        return "redirect:/zephyr/color";
    }

    @RequestMapping(value = "/zephyr/color/delete/{idColor}", method = RequestMethod.GET)
    public String deleteColor(@PathVariable("idColor") Long idColor) {
        service.deleteColor(idColor);
        return "redirect:/zephyr/color";
    }

    // CONTROLLER ORIGIN
    @RequestMapping(value = "/zephyr/origin", method = RequestMethod.GET)
    public String viewOrigin(Model model,
                             @RequestParam(defaultValue = "0") int page,
                             @ModelAttribute("phoneNumber") String phoneNumber,
                             @ModelAttribute("password") String password) {
        Staff detailStaff = service.detailStaffByPhoneNumberAndPassword(phoneNumber, password);
        model.addAttribute("detailStaff", detailStaff);
        Page<Origin> itemsOrigins = service.getAllOrigins(PageRequest.of(page, 5));
        model.addAttribute("listOrigin", itemsOrigins);
        model.addAttribute("currentPage", page);
        model.addAttribute("main", "/WEB-INF/view/detail/origin/listOrigin.jsp");
        return "home_page/staff";
    }

    @RequestMapping(value = "/zephyr/origin/view-add", method = RequestMethod.GET)
    public String viewAddOrigin(Model model,
                                @ModelAttribute("phoneNumber") String phoneNumber,
                                @ModelAttribute("password") String password) {
        Origin origin = new Origin();
        Staff detailStaff = service.detailStaffByPhoneNumberAndPassword(phoneNumber, password);
        model.addAttribute("detailStaff", detailStaff);
        model.addAttribute("origin", origin);
        model.addAttribute("main", "/WEB-INF/view/detail/origin/view-add.jsp");
        return "home_page/staff";
    }

    @RequestMapping(value = "/zephyr/origin/add", method = RequestMethod.POST)
    public String addOrigin(@ModelAttribute("origin") Origin origin,
                            @ModelAttribute("phoneNumber") String phoneNumber,
                            @ModelAttribute("password") String password) {
        System.out.println("ddddddddddddddddđ : " + phoneNumber);
        System.out.println("bgggggggggggggggggggg : " + password);
        Staff detailStaff = service.detailStaffByPhoneNumberAndPassword(phoneNumber, password);
        System.out.println("tttttttttttttttt : " + detailStaff);
        Origin newOrigin1 = Origin.builder()
                .codeOrigin(generateRandomString())
                .nameOrigin(origin.getNameOrigin())
                .dateCreate(LocalDate.now())
                .dateUpdate(LocalDate.now())
                .userCreate(detailStaff.getName())
                .userUpdate(detailStaff.getName())
                .status(1)
                .build();
        service.addOrigin(newOrigin1);
        return "redirect:/zephyr/origin";
    }

    @RequestMapping(value = "/zephyr/origin/detail/{idOrigin}", method = RequestMethod.GET)
    public String detailOrigin(Model model,
                               @PathVariable("idOrigin") Long idOrigin,
                               @ModelAttribute("phoneNumber") String phoneNumber,
                               @ModelAttribute("password") String password) {
        Origin detailOrigin = service.detailOrigin(idOrigin);
        Staff detailStaff = service.detailStaffByPhoneNumberAndPassword(phoneNumber, password);
        model.addAttribute("detailStaff", detailStaff);
        model.addAttribute("detailOrigin", detailOrigin);
        model.addAttribute("main", "/WEB-INF/view/detail/origin/view-update.jsp");
        return "home_page/staff";
    }

    @RequestMapping(value = "/zephyr/origin/update", method = RequestMethod.POST)
    public String updateOrigin(@ModelAttribute("detailOrigin") Origin origin,
                               @ModelAttribute("phoneNumber") String phoneNumber,
                               @ModelAttribute("password") String password) {
        System.out.println("ddddddddddddddddđ : " + phoneNumber);
        System.out.println("bgggggggggggggggggggg : " + password);
        Staff detailStaff = service.detailStaffByPhoneNumberAndPassword(phoneNumber, password);
        System.out.println("tttttttttttttttt : " + detailStaff);
        Origin detailOrigin1 = service.detailOrigin(origin.getIdOrigin());
        Origin newOrigin1 = Origin.builder()
                .idOrigin(detailOrigin1.getIdOrigin())
                .codeOrigin(detailOrigin1.getCodeOrigin())
                .nameOrigin(origin.getNameOrigin())
                .dateCreate(detailOrigin1.getDateCreate())
                .dateUpdate(LocalDate.now())
                .userCreate(detailStaff.getName())
                .userUpdate(detailStaff.getName())
                .status(origin.getStatus())
                .build();
        service.addOrigin(newOrigin1);
        return "redirect:/zephyr/origin";
    }

    @RequestMapping(value = "/zephyr/origin/delete/{idOrigin}", method = RequestMethod.GET)
    public String deleteOrigin(@PathVariable("idOrigin") Long idOrigin) {
        service.deleteOrigin(idOrigin);
        return "redirect:/zephyr/origin";
    }

    //CONTROLLER SIZE
    @RequestMapping(value = "/zephyr/size", method = RequestMethod.GET)
    public String viewSize(Model model,
                           @RequestParam(defaultValue = "0") int page,
                           @ModelAttribute("phoneNumber") String phoneNumber,
                           @ModelAttribute("password") String password) {
        Staff detailStaff = service.detailStaffByPhoneNumberAndPassword(phoneNumber, password);
        model.addAttribute("detailStaff", detailStaff);
        Page<Size> itemsSizes = service.getAllSizes(PageRequest.of(page, 5));
        model.addAttribute("listSize", itemsSizes);
        model.addAttribute("currentPage", page);
        model.addAttribute("main", "/WEB-INF/view/detail/size/listSize.jsp");
        return "home_page/staff";
    }

    @RequestMapping(value = "/zephyr/size/view-add", method = RequestMethod.GET)
    public String viewAddSize(Model model,
                              @ModelAttribute("phoneNumber") String phoneNumber,
                              @ModelAttribute("password") String password) {
        Size size = new Size();
        Staff detailStaff = service.detailStaffByPhoneNumberAndPassword(phoneNumber, password);
        model.addAttribute("detailStaff", detailStaff);
        model.addAttribute("size", size);
        model.addAttribute("main", "/WEB-INF/view/detail/size/view-add.jsp");
        return "home_page/staff";
    }

    @RequestMapping(value = "/zephyr/size/add", method = RequestMethod.POST)
    public String addSize(@ModelAttribute("size") Size size,
                          @ModelAttribute("phoneNumber") String phoneNumber,
                          @ModelAttribute("password") String password) {
        System.out.println("ddddddddddddddddđ : " + phoneNumber);
        System.out.println("bgggggggggggggggggggg : " + password);
        Staff detailStaff = service.detailStaffByPhoneNumberAndPassword(phoneNumber, password);
        System.out.println("tttttttttttttttt : " + detailStaff);
        Size newSize1 = Size.builder()
                .codeSize(generateRandomString())
                .nameSize(size.getNameSize())
                .dateCreate(LocalDate.now())
                .dateUpdate(LocalDate.now())
                .userCreate(detailStaff.getName())
                .userUpdate(detailStaff.getName())
                .status(1)
                .build();
        service.addSize(newSize1);
        return "redirect:/zephyr/size";
    }

    @RequestMapping(value = "/zephyr/size/detail/{idSize}", method = RequestMethod.GET)
    public String detailSize(Model model,
                             @PathVariable("idSize") Long idSize,
                             @ModelAttribute("phoneNumber") String phoneNumber,
                             @ModelAttribute("password") String password) {
        Size detailSize = service.detailSize(idSize);
        Staff detailStaff = service.detailStaffByPhoneNumberAndPassword(phoneNumber, password);
        model.addAttribute("detailStaff", detailStaff);
        model.addAttribute("detailSize", detailSize);
        model.addAttribute("main", "/WEB-INF/view/detail/size/view-update.jsp");
        return "home_page/staff";
    }

    @RequestMapping(value = "/zephyr/size/update", method = RequestMethod.POST)
    public String updateSize(@ModelAttribute("detailSize") Size size,
                             @ModelAttribute("phoneNumber") String phoneNumber,
                             @ModelAttribute("password") String password) {
        System.out.println("ddddddddddddddddđ : " + phoneNumber);
        System.out.println("bgggggggggggggggggggg : " + password);
        Staff detailStaff = service.detailStaffByPhoneNumberAndPassword(phoneNumber, password);
        System.out.println("tttttttttttttttt : " + detailStaff);
        Size detailSize1 = service.detailSize(size.getIdSize());
        Size newSize1 = Size.builder()
                .idSize(detailSize1.getIdSize())
                .codeSize(detailSize1.getCodeSize())
                .nameSize(size.getNameSize())
                .dateCreate(detailSize1.getDateCreate())
                .dateUpdate(LocalDate.now())
                .userCreate(detailStaff.getName())
                .userUpdate(detailStaff.getName())
                .status(size.getStatus())
                .build();
        service.addSize(newSize1);
        return "redirect:/zephyr/size";
    }

    @RequestMapping(value = "/zephyr/size/delete/{idSize}", method = RequestMethod.GET)
    public String deleteSize(@PathVariable("idSize") Long idSize) {
        service.deleteSize(idSize);
        return "redirect:/zephyr/size";
    }

    // CONTROLLER PRODUCTDETAIL
    @RequestMapping(value = "/zephyr/productdetail", method = RequestMethod.GET)
    public String viewProductDetail(Model model,
                                    @RequestParam(defaultValue = "0") int page,
                                    @ModelAttribute("phoneNumber") String phoneNumber,
                                    @ModelAttribute("password") String password) {
        Staff detailStaff = service.detailStaffByPhoneNumberAndPassword(phoneNumber, password);
        model.addAttribute("detailStaff", detailStaff);
        Page<ProductDetail> itemsProductDetails = service.getAllProductDetails(PageRequest.of(page, 5));
        model.addAttribute("listProductDetail", itemsProductDetails);
        model.addAttribute("currentPage", page);
        model.addAttribute("main", "/WEB-INF/view/detail/productDetail/listProductDetail.jsp");
        return "home_page/staff";
    }

    @RequestMapping(value = "/zephyr/productdetail/view-add", method = RequestMethod.GET)
    public String viewAddProductDetail(Model model,
                                       @ModelAttribute("phoneNumber") String phoneNumber,
                                       @ModelAttribute("password") String password) {
        ProductDetail productDetail = new ProductDetail();
        listProduct = service.getAllProduct1();
        listColor = service.getAllColors1();
        listOrigin = service.getAllOrigins1();
        listSize = service.getAllSizes1();
        Staff detailStaff = service.detailStaffByPhoneNumberAndPassword(phoneNumber, password);
        model.addAttribute("detailStaff", detailStaff);
        model.addAttribute("productDetail", productDetail);
        model.addAttribute("listProduct", listProduct);
        model.addAttribute("listColor", listColor);
        model.addAttribute("listOrigin", listOrigin);
        model.addAttribute("listSize", listSize);
        model.addAttribute("main", "/WEB-INF/view/detail/productDetail/view-add.jsp");
        return "home_page/staff";
    }

    @RequestMapping(value = "/zephyr/productdetail/add", method = RequestMethod.POST)
    public String addProductDetail(@ModelAttribute("productDetail") ProductDetail productDetail,
                                   @ModelAttribute("phoneNumber") String phoneNumber,
                                   @ModelAttribute("password") String password) {
        System.out.println("ddddddddddddddddđ : " + phoneNumber);
        System.out.println("bgggggggggggggggggggg : " + password);
        Staff detailStaff = service.detailStaffByPhoneNumberAndPassword(phoneNumber, password);
        System.out.println("tttttttttttttttt : " + detailStaff);
        ProductDetail newProductDetail1 = ProductDetail.builder()
                .images(productDetail.getImages())
                .describe(productDetail.getDescribe())
                .inventory(productDetail.getInventory())
                .importPrice(productDetail.getImportPrice())
                .price(productDetail.getPrice())
                .status(1)
                .product(productDetail.getProduct())
                .origin(productDetail.getOrigin())
                .color(productDetail.getColor())
                .size(productDetail.getSize())
                .build();
        service.addProductDetail(newProductDetail1);
        return "redirect:/zephyr/productdetail";
    }

    @RequestMapping(value = "/zephyr/productdetail/detail/{idProductDetail}", method = RequestMethod.GET)
    public String detailProductDetail(Model model,
                                      @PathVariable("idProductDetail") Long idProductDetail,
                                      @ModelAttribute("phoneNumber") String phoneNumber,
                                      @ModelAttribute("password") String password) {
        ProductDetail detailProductDetail = service.detailProductDetail(idProductDetail);
        listProduct = service.getAllProduct1();
        listColor = service.getAllColors1();
        listOrigin = service.getAllOrigins1();
        listSize = service.getAllSizes1();
        Staff detailStaff = service.detailStaffByPhoneNumberAndPassword(phoneNumber, password);
        model.addAttribute("detailStaff", detailStaff);
        model.addAttribute("detailProductDetail", detailProductDetail);
        model.addAttribute("listProduct", listProduct);
        model.addAttribute("listColor", listColor);
        model.addAttribute("listOrigin", listOrigin);
        model.addAttribute("listSize", listSize);
        model.addAttribute("main", "/WEB-INF/view/detail/productDetail/view-update.jsp");
        return "home_page/staff";
    }

    @RequestMapping(value = "/zephyr/productdetail/update", method = RequestMethod.POST)
    public String updateProductDetail(@ModelAttribute("detailProductDetail") ProductDetail productDetail,
                                      @RequestParam("images") MultipartFile file,
                                      @ModelAttribute("phoneNumber") String phoneNumber,
                                      @ModelAttribute("password") String password) throws IOException {
        System.out.println("ddddddddddddddddđ : " + phoneNumber);
        System.out.println("bgggggggggggggggggggg : " + password);
        Staff detailStaff = service.detailStaffByPhoneNumberAndPassword(phoneNumber, password);
        System.out.println("tttttttttttttttt : " + detailStaff);
        ProductDetail detailProductDetail1 = service.detailProductDetail(productDetail.getIdProductDetail());

        String oldImagePath = service.detailProductDetail(productDetail.getIdProductDetail()).getImages();
        if (file.isEmpty()) {
            productDetail.setImages(oldImagePath);
        } else {
            String fileName = file.getOriginalFilename();
            String imagePath = "/assets/images/" + fileName;
            file.transferTo(new File(imagePath));
            productDetail.setImages(imagePath);
        }

        ProductDetail newProductDetail1 = ProductDetail.builder()
                .idProductDetail(detailProductDetail1.getIdProductDetail())
                .images(productDetail.getImages())
                .describe(productDetail.getDescribe())
                .inventory(productDetail.getInventory())
                .importPrice(productDetail.getImportPrice())
                .price(productDetail.getPrice())
                .status(1)
                .product(productDetail.getProduct())
                .origin(productDetail.getOrigin())
                .color(productDetail.getColor())
                .size(productDetail.getSize())
                .build();
        service.addProductDetail(newProductDetail1);
        return "redirect:/zephyr/productdetail";
    }

    @RequestMapping(value = "/zephyr/productdetail/delete/{idProductDetail}", method = RequestMethod.GET)
    public String deleteProductDetail(@PathVariable("idProductDetail") Long idProductDetail) {
        service.deleteProductDetail(idProductDetail);
        return "redirect:/zephyr/productdetail";
    }
}

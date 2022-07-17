package com.example.ecommercemart.controller;

import com.example.ecommercemart.global.GlobalData;
import com.example.ecommercemart.service.CategoryService;
import com.example.ecommercemart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @GetMapping({"/", "/home"})
    public String home(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        return "index";
    }

    @GetMapping("/shop")
    public String shop(Model model){
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProduct());
        model.addAttribute("cartCount", GlobalData.cart.size());
        return "shop";
    }

    @GetMapping("/shop/category/{id}")
    public String shopByCategory(Model model, @PathVariable int id){
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("products", productService.getProductsByCategoryId(id));
        return "shop";
    }

    @GetMapping("/shop/viewProduct/{id}")
    public String viewProduct(Model model, @PathVariable int id){
        model.addAttribute("product", productService.getProductById((long) id).get());
        model.addAttribute("cartCount", GlobalData.cart.size());
        return "viewProduct";
    }

//    @GetMapping("/cart/removeItem/{index}")
//    public String cartItemRemove(@PathVariable int index){
//        GlobalData.cart.remove(index);
//        return "redirect:/cart";
//    }
//    @GetMapping("/checkout")
//    public String checkout(Model model){
//
//    }
}

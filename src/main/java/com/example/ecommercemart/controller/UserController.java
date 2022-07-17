package com.example.ecommercemart.controller;

import com.example.ecommercemart.dto.UserDTO;
import com.example.ecommercemart.model.User;
import com.example.ecommercemart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/register")
    public String addUserGet(Model model){
        model.addAttribute("userDTO", new UserDTO());
        return "register";
    }
    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("login", new UserDTO());
        return "login";
    }
    @PostMapping("/register")
    public String addUserPost(@ModelAttribute("userDTO") UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        userService.addUser(user);
        return "redirect:/Login";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model ) {
        User oauthUser = userService.authenticate(user.getEmail(), user.getPassword());
        if (oauthUser.getEmail().equals("admin@gmail.com") && oauthUser.getPassword().equals("admin")) {
            return "redirect:/adminHome";
        }
        if(oauthUser != null) {
            model.addAttribute("user", oauthUser);
            return "redirect:/shop";
        } else {
            return "redirect:/Login";
        }
    }
    @GetMapping("/product")
    public String product(){
        return "product";
    }
    @GetMapping("/adminHome")
    public String adminHome(){
        return "adminHome";
    }
    @GetMapping("/logout")
    public String userLogout(){
        return "index";
    }

}

package com.denarisolutions.denariapp_springboot.controllers;

import com.denarisolutions.denariapp_springboot.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private final UserRepository adDao;


    public UserController(UserRepository adDao) {
        this.adDao = adDao;
    }

    @GetMapping("/register")
    public String registrationForm() {
        return "users/register";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "users/login";
    }

    @GetMapping("/basic_info")
    public String basicInfoForm() {
        return "users/basic_info";
    }

    @GetMapping("/address_form")
    public String addressForm() {
        return "users/address_form";
    }

    @GetMapping("/rent_info")
    public String rentForm() {
        return "users/rent_info";
    }
}

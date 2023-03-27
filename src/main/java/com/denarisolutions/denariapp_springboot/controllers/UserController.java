package com.denarisolutions.denariapp_springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/register")
    public String registrationForm() {
        return "users/register";
    }

    @GetMapping("/basic_info")
    public String basicInfoForm() {
        return "users/basic_info";
    }

}

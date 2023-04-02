package com.denarisolutions.denariapp_springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/Landing")
    public String homePage() {
        return "index";
    }

}

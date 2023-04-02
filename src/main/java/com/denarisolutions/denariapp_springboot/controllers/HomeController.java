package com.denarisolutions.denariapp_springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/Landing")
    public String homePage() {
        return "index";
    }

    @GetMapping("/Pricing")
    public String pricingPage(){return "pricing";}

    @GetMapping("/AboutUs")
    public String aboutUsPage(){return "aboutUs";}

}

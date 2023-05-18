package com.denarisolutions.denariapp_springboot.controllers;

import com.denarisolutions.denariapp_springboot.models.User;
import com.denarisolutions.denariapp_springboot.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user){
        user = userDao.findById(user.getId());
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/login";
    }

//    @GetMapping("/profile")
//    public String showProfile(Model model) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        user = userDao.findById(user.getId());
////        List<BookClub> bookClubs = user.getBookClubs();
////        model.addAttribute("bookClubs", bookClubs);
//        model.addAttribute("user", user);
//        return "users/profile";
//    }
//
//    @PostMapping("/profile")
//    public String editProfile(@ModelAttribute("user") User user, Principal principal) {
//        String hash = passwordEncoder.encode(user.getPassword());
//        user.setPassword(hash);
//        userDao.save(user);
//        return "redirect:/profile";
//    }

//    @GetMapping("/login")
//    public String loginForm() {
//        return "users/login";
//    }
//
//    @GetMapping("/basic_info")
//    public String basicInfoForm() {
//        return "users/basic_info";
//    }
//
//    @GetMapping("/address_form")
//    public String addressForm() {
//        return "users/address_form";
//    }
//
//    @GetMapping("/rent_info")
//    public String rentForm() {
//        return "users/rent_info";
//    }

}

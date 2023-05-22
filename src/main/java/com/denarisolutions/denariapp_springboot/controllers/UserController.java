package com.denarisolutions.denariapp_springboot.controllers;

import com.denarisolutions.denariapp_springboot.models.User;
import com.denarisolutions.denariapp_springboot.repositories.AddressRepository;
import com.denarisolutions.denariapp_springboot.repositories.PersonalInfoRepository;
import com.denarisolutions.denariapp_springboot.repositories.RentalDataRepository;
import com.denarisolutions.denariapp_springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class UserController {
    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;
    private final PersonalInfoRepository basicInfoDao;
    private final AddressRepository addressDao;
    private final RentalDataRepository rentalDataDao;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder, PersonalInfoRepository basicInfoDao, AddressRepository addressDao, RentalDataRepository rentalDataDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.basicInfoDao = basicInfoDao;
        this.addressDao = addressDao;
        this.rentalDataDao = rentalDataDao;
    }

    @GetMapping("/register")
    public String registrationForm(Model model){
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String showProfile(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userDao.findById(user.getId());
        model.addAttribute("user", user);
        return "users/profile";
    }

//    @GetMapping("/basic_info")
//    public String basicInfoForm(Model model) {
//        model.addAttribute("info", basicInfoDao.findAll());
//        return "users/basic_info";
//    }
//
//    @PostMapping("/basic_info")
//    public String saveBasicInfo(@ModelAttribute Info info) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        basicInfoDao.save(info);
//        return "users/address_form";
//    }

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

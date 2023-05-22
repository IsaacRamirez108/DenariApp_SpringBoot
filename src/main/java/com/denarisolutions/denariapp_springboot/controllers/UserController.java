package com.denarisolutions.denariapp_springboot.controllers;

import com.denarisolutions.denariapp_springboot.models.*;
import com.denarisolutions.denariapp_springboot.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class UserController {
    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;
    private final PersonalInfoRepository personalInfoDao;
    private final AddressRepository addressDao;
    private final RentalDataRepository rentalDataDao;
    private final PropertyManagementRepository propertyManagementDao;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder, PersonalInfoRepository personalInfoDao,
                          AddressRepository addressDao, RentalDataRepository rentalDataDao,
                          PropertyManagementRepository propertyManagementDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.personalInfoDao = personalInfoDao;
        this.addressDao = addressDao;
        this.rentalDataDao = rentalDataDao;
        this.propertyManagementDao = propertyManagementDao;
    }

    @GetMapping("/register")
    public String registrationForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("personalInfo", new PersonalInfo());
        model.addAttribute("address", new Address());
        model.addAttribute("rentalData", new RentalData());
        model.addAttribute("propertyManagement", new PropertyManagement());
        return "users/register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user, @ModelAttribute PersonalInfo personalInfo, @ModelAttribute Address address, @ModelAttribute RentalData rentalData,@ModelAttribute PropertyManagement propertyManagement){
        personalInfo = new PersonalInfo();
        personalInfo.setUser(user);

        rentalData = new RentalData();
        rentalData.setUser(user);

        address = new Address();
        address.setUsers((Set<User>) user);

        propertyManagement = new PropertyManagement();
        propertyManagement.setUsers((Set<User>) user);

        // Save the entities
        personalInfoDao.save(personalInfo);
        rentalDataDao.save(rentalData);
        addressDao.save(address);
        propertyManagementDao.save(propertyManagement);
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

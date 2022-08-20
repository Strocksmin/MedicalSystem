package ru.medcity.medicalsystem.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.medcity.medicalsystem.DTO.UserData;

public class LoginController {

    @GetMapping("/login")
    private String getLogin(Model model) {
        model.addAttribute("user", new UserData());
        System.out.println("На странице логин");
        return "login";
    }

    @PostMapping("/login")
    private String postLogin(Model model) {
        model.addAttribute("user", new UserData());
        System.out.println("Нажал на кнопку войти");
        return "login";
    }
}

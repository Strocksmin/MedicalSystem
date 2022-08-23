package ru.medcity.medicalsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    private String getLogin() {
        System.out.println("On login page");
        return "login";
    }
}

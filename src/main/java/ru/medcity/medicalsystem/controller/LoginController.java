package ru.medcity.medicalsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.medcity.medicalsystem.DTO.UserData;

@Controller
public class LoginController {

    @GetMapping("/login")
    private String getLogin() {
        System.out.println("On login page");
        return "login";
    }
}

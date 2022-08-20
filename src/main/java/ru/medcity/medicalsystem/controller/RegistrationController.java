package ru.medcity.medicalsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.medcity.medicalsystem.DTO.UserData;

@Controller
public class RegistrationController {
    @GetMapping("/signup")
    private String getRegistration(Model model) {
        model.addAttribute("user", new UserData());
        System.out.println("На странице регистрации");
        return "signup";
    }

    @PostMapping("/signup")
    private String postRegistration() {
        System.out.println("Нажата кнопка регистрации");
        return "redirect:/index";
    }
}

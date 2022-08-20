package ru.medcity.medicalsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.medcity.medicalsystem.DTO.UserData;
import ru.medcity.medicalsystem.exception.UserAlreadyExistsException;
import ru.medcity.medicalsystem.model.User;
import ru.medcity.medicalsystem.service.UserService;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    @GetMapping("/registration")
    private String getRegistration(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("registration")
    private String postRegistration(@Valid @ModelAttribute("user") User userdata, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("user", userdata);
            return "signup";
        }
        try {
            userService.registerUser(userdata);
        } catch (UserAlreadyExistsException alreadyExistsException) {
            bindingResult.rejectValue("email", "user.email","Аккаунт с такой почтой уже существует.");
            model.addAttribute("user", userdata);
            return "signup";
        }
        return "redirect:/adminpanel";
    }
}

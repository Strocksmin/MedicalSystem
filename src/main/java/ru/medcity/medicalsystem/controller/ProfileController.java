package ru.medcity.medicalsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.medcity.medicalsystem.service.ClientService;
import ru.medcity.medicalsystem.service.DoctorService;
import ru.medcity.medicalsystem.service.UserService;

@Controller
public class ProfileController {
    @Autowired
    ClientService clientService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    UserService userService;

    @GetMapping("/profile")
    private String getProfile() {
        Authentication principal = SecurityContextHolder.getContext().getAuthentication();
        for (GrantedAuthority grantedAuthority: principal.getAuthorities()) {
            if (grantedAuthority.getAuthority().equals("ADMIN")) {
                return "redirect:/adminpanel";
            }
        }
        for (GrantedAuthority grantedAuthority: principal.getAuthorities()) {
            if (grantedAuthority.getAuthority().equals("DOCTOR")) {
                return "redirect:/doctorprofile";
            }
        }
        return "redirect:/clientprofile";
    }

    @GetMapping("/doctorprofile")
    public String getDoctorProfile(Model model) {
        Authentication principal = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("clients", doctorService.getDoctorByDoctorUser(principal.getName()).getClients());
        model.addAttribute("doctor", doctorService.getDoctorByDoctorUser(principal.getName()));
        return "doctorprofile";
    }

    @GetMapping("/clientprofile")
    public String getUserProfile(Model model) {
        Authentication principal = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        model.addAttribute("clients", clientService.getClientsByEmail(principal.getName()));
        return "clientprofile";
    }
}

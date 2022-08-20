package ru.medcity.medicalsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.medcity.medicalsystem.model.Doctor;
import ru.medcity.medicalsystem.model.Proposal;
import ru.medcity.medicalsystem.service.DoctorService;
import ru.medcity.medicalsystem.service.ProposalService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AppointmentController {

    @Autowired
    ProposalService proposalService;
    @Autowired
    DoctorService doctorService;

    @GetMapping("appointment")
    public String appointment(Model model) {
        model.addAttribute("proposal", new Proposal());
        model.addAttribute("doctors", doctorService.getDoctors());
        return "appointment";
    }

    @PostMapping("appointment")
    public String postAppointment(@Valid @ModelAttribute("proposal") Proposal appointmentData, BindingResult bindingResult, final Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("proposal", appointmentData);
            model.addAttribute("doctors", doctorService.getDoctors());
            return "appointment";
        }
        proposalService.addProposal(appointmentData);
        return "redirect:/index";
    }

    @GetMapping("appointment/proposals")
    public @ResponseBody List<Proposal> proposals(Model model) {
        return proposalService.getProposals();
    }
}

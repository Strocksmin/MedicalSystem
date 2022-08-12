package ru.medcity.medicalsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.medcity.medicalsystem.DTO.AppointmentData;
import ru.medcity.medicalsystem.model.Proposal;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AppointmentController {

    ArrayList<Proposal> proposals = new ArrayList<>();

    @GetMapping("appointment")
    public String appointment(Model model) {
        model.addAttribute("appointmentData", new AppointmentData());
        return "appointment";
    }

    @PostMapping("appointment")
    public String postAppointment(final AppointmentData appointmentData, final Model model) {
        proposals.add(new Proposal(0, appointmentData.getName(),appointmentData.getLastname(),
                appointmentData.getEmail(),appointmentData.getNumber(), appointmentData.getSpecialization(),
                appointmentData.getDatetime()));
        return "appointment";
    }

    @GetMapping("appointment/proposals")
    public @ResponseBody List<Proposal> proposals(Model model) {
        return proposals;
    }
}

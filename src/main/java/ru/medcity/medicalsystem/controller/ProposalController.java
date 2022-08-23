package ru.medcity.medicalsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.medcity.medicalsystem.model.Doctor;
import ru.medcity.medicalsystem.service.DoctorService;

import java.util.List;

@Controller
public class ProposalController {

    @Autowired
    DoctorService doctorService;

    @GetMapping("doctors")
    public @ResponseBody List<Doctor> getdoctors(Model model) {
        return doctorService.getDoctors();
    }
}

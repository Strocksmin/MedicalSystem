package ru.medcity.medicalsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.medcity.medicalsystem.DTO.MessageData;

@Controller
public class ProposalController {
    @GetMapping("proposals")
    public String getProposals(Model model) {
        return "proposals";
    }
}

package ru.medcity.medicalsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.medcity.medicalsystem.DTO.MessageData;
import ru.medcity.medicalsystem.service.ProposalService;

@Controller
public class AdminPanelController {
    @Autowired
    ProposalService proposalService;

    @GetMapping("adminpanel")
    public String adminpanel(Model model) {
        model.addAttribute("proposals", proposalService.getProposals());
        return "adminpanel";
    }
}

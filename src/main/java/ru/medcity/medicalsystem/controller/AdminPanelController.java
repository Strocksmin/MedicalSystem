package ru.medcity.medicalsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.medcity.medicalsystem.DTO.MessageData;
import ru.medcity.medicalsystem.model.Client;
import ru.medcity.medicalsystem.model.Proposal;
import ru.medcity.medicalsystem.service.ClientService;
import ru.medcity.medicalsystem.service.DoctorService;
import ru.medcity.medicalsystem.service.ProposalService;

@Controller
public class AdminPanelController {
    @Autowired
    ProposalService proposalService;
    @Autowired
    ClientService clientService;
    @Autowired
    DoctorService doctorService;

    @GetMapping("/adminpanel")
    public String adminpanel(Model model) {
        model.addAttribute("proposals", proposalService.getProposals());
        return "adminpanel";
    }

    @GetMapping("/adminpanel/edit/{id}")
    public String editProposal(Model model, @PathVariable int id) {
        model.addAttribute("proposal", proposalService.getProposal(id));
        model.addAttribute("doctors", doctorService.getDoctors());
        return "editproposal";
    }

    @PostMapping("/adminpanel/postedit/{id}")
    public String postEditProposal(Model model, @PathVariable int id, @ModelAttribute("proposal") Proposal proposal) {
        Proposal newProposal = proposalService.getProposal(id);
        newProposal.setId(id);
        newProposal.setDatetime(proposal.getDatetime());
        newProposal.setName(proposal.getName());
        newProposal.setLastname(proposal.getLastname());
        newProposal.setSpecialization(proposal.getSpecialization());
        newProposal.setEmail(proposal.getEmail());
        newProposal.setNumber(proposal.getNumber());
        proposalService.addProposal(newProposal);
        return "redirect:/adminpanel";
    }

    @GetMapping("/adminpanel/delete/{id}")
    public String deleteProposal(Model model, @PathVariable int id) {
        proposalService.deleteProposal(id);
        return "redirect:/adminpanel";
    }

    @GetMapping("/adminpanel/accept/{id}")
    public String acceptProposal(Model model, @PathVariable int id) {
        Proposal proposal = proposalService.getProposal(id);
        clientService.addClient(new Client(proposal.getName(), proposal.getLastname(),
                proposal.getEmail(), proposal.getNumber(),
                doctorService.getDoctorForProposal(proposal.getSpecialization()).getId(),
                proposal.getDatetime()));
        proposalService.deleteProposal(id);
        return "redirect:/adminpanel";
    }

    @GetMapping("/adminpanel/clients")
    public String getClients(Model model) {
        model.addAttribute("clients", clientService.getClients());
        return "clients";
    }

}

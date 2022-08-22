package ru.medcity.medicalsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.medcity.medicalsystem.DTO.MessageData;
import ru.medcity.medicalsystem.DTO.RoleData;
import ru.medcity.medicalsystem.model.Client;
import ru.medcity.medicalsystem.model.Proposal;
import ru.medcity.medicalsystem.model.Role;
import ru.medcity.medicalsystem.model.User;
import ru.medcity.medicalsystem.service.*;

import java.util.Collection;
import java.util.Set;

@Controller
public class AdminPanelController {
    @Autowired
    ProposalService proposalService;
    @Autowired
    ClientService clientService;
    @Autowired
    DoctorService doctorService;

    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;

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

    @GetMapping("/adminpanel/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @GetMapping("/adminpanel/edituser/{id}")
    public String getEditUser(@PathVariable int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getRoles());
        model.addAttribute("rol", new RoleData());
        return "editUser";
    }

    @PostMapping("/adminpanel/postedituser/{id}")
    public String postEditUser(Model model, @PathVariable int id,  @ModelAttribute("user") User userdata, @ModelAttribute("rol") RoleData rol) {
        User newUser = userService.getUserById(id);
        Collection<Role> roles = newUser.getRoles();
        if (!roles.contains(userService.getRole(rol.getRoleN())))
            roles.add(userService.getRole(rol.getRoleN()));
        newUser.setId(id);
        newUser.setName(userdata.getName());
        newUser.setName(userdata.getLastname());
        newUser.setName(userdata.getName());
        newUser.setRoles(roles);
        userService.upgradeUser(newUser);
        return "redirect:/adminpanel";
    }
}
package ru.medcity.medicalsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.medcity.medicalsystem.DTO.MessageData;
import ru.medcity.medicalsystem.model.Proposal;
import ru.medcity.medicalsystem.service.EmailServiceImpl;

@Controller
public class IndexController {
    @Autowired
    EmailServiceImpl emailService;

    @GetMapping("index")
    public String index(Model model) {
        model.addAttribute("messageData", new MessageData());
        return "index";
    }

    @PostMapping("sendMessage")
    public String sendMessage(final MessageData messageData, final Model model) {
        System.out.println("Текст = " + messageData.getText());
        emailService.sendSimpleEmail(messageData);
        return "redirect:index";
    }

    @GetMapping("team")
    public String getTeam(Model model) {
        return "team";
    }

    @GetMapping("services")
    public String get(Model model) {
        return "services";
    }

    // TODO: Добавить еженедельную рассылку почты
}

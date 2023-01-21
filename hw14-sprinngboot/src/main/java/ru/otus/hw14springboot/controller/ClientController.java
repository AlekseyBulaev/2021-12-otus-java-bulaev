package ru.otus.hw14springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import ru.otus.hw14springboot.model.Client;
import ru.otus.hw14springboot.service.ClientService;

@Controller
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/index")
    public String getHomePage(Model model) {
        model.addAttribute("clients", clientService.findAll());
        return "index";
    }

    @PostMapping("/addclient")
    public String addClient(Client client, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-client";
        }

        clientService.addClient();
        return "redirect:/index";
    }


    @GetMapping("/signup")
    public String showSignUpForm(Client client) {
        return "add-client";
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable("id") long id, Model model) {
        Client client = clientService.findById(id);
        clientService.delete(client);
        return "redirect:/index";
    }

    @GetMapping("/update/{id}")
    public String updateClient(@PathVariable("id") long id, Model model) {
        clientService.updateClient(id);
        return "redirect:/index";
    }
}

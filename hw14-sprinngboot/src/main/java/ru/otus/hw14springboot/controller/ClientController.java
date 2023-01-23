package ru.otus.hw14springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import ru.otus.hw14springboot.dto.ClientDto;
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
    public String addClient(ClientDto clientDto, BindingResult result) {
        if (result.hasErrors()) {
            return "add-client";
        }

        clientService.addClient(clientDto);
        return "redirect:/index";
    }


    @GetMapping("/signup")
    public String showSignUpForm(ClientDto client) {
        return "add-client";
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable("id") long id) {
        ClientDto client = clientService.findById(id);
        clientService.deleteClient(client);
        return "redirect:/index";
    }

    @PostMapping("/update/{id}")
    public String updateClient(@PathVariable("id") long id, ClientDto clientDto,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            clientDto.setId(id);
            return "update-client";
        }
        clientService.updateClient(clientDto);
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        ClientDto client = clientService.findById(id);

        model.addAttribute("client", client);
        return "update-client";
    }
}

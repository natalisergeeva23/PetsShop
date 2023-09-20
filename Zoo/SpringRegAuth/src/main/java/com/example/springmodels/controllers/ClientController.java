package com.example.springmodels.controllers;

import com.example.springmodels.models.Client;
import com.example.springmodels.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('USER','ADMIN','MANAGER')")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/client")
    public String clientMain(Model model) {
        Iterable<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);
        return "client";
    }

    @GetMapping("/client/add")
    public String clientAdd(@ModelAttribute("clients") Client client) {
        return "client-add";
    }

    @PostMapping("/client/add")
    public String clientPostAdd(@ModelAttribute("clients") @Valid Client client, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "client-add";
        }
        clientRepository.save(client);
        return "redirect:/client";
    }

    @GetMapping("/client/{id}/edit")
    public  String clientDetails(@PathVariable(value = "id") int id, Model model)
    {
        Client client = clientRepository.findById(id).orElseThrow();
        model.addAttribute("client", client);
        return "client-edit";
    }

    @PostMapping ("/client/{id}/edit")
    public  String clientUpdate(@ModelAttribute("client") @Valid Client client,
                                BindingResult bindingResult,
                                @PathVariable(value = "id") long id)
    {
        if(bindingResult.hasErrors())
            return "client-edit";
        clientRepository.save(client);
        return "redirect:/client";
    }

    @GetMapping("/client/{id}/remove")
    public  String clientDelete(@PathVariable(value = "id") int id, Model model)
    {
        Client client = clientRepository.findById(id).orElseThrow();
        clientRepository.delete(client);
        return "redirect:/client";
    }

    @RequestMapping(value="/getItem", method= RequestMethod.GET)
    public String getItem(@RequestParam String firstNameClient, Model model) {
        if(firstNameClient.isEmpty())
        {
            return "error";
        }
        Client clients = clientRepository.findByFirstNameClient(firstNameClient);
        model.addAttribute("clients", clients);
        return "client";
    }
}

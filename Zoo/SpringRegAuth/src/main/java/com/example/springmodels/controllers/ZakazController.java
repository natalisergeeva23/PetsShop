package com.example.springmodels.controllers;

import com.example.springmodels.models.Client;
import com.example.springmodels.models.Product;
import com.example.springmodels.models.Zakaz;
import com.example.springmodels.repository.ClientRepository;
import com.example.springmodels.repository.ProductRepository;
import com.example.springmodels.repository.ZakazRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@PreAuthorize("hasAnyAuthority('USER','MANAGER','ADMIN')")
public class ZakazController {

    @Autowired
    private ZakazRepository zakazRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/zakaz")
    public String zakazMain(Model model) {
        Iterable<Zakaz> zakazes = zakazRepository.findAll();
        model.addAttribute("zakazes", zakazes);
        return "zakaz";
    }

    @GetMapping("/zakaz/add")
    public String zakazAdd(@ModelAttribute("zakazes") Zakaz zakaz, Model model) {
        Iterable<Product> products = productRepository.findAll();
        Iterable<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);
        model.addAttribute("products", products);
        return "zakaz-add";
    }

    @PostMapping("/zakaz/add")
    public String orderPostAdd(@ModelAttribute("zakazes") @Valid Zakaz zakaz,
                               BindingResult bindingResult,
                               @RequestParam String nameProduct,
                               @RequestParam String firstNameClient,
                               Model model) {
        if(bindingResult.hasErrors()) {
            Iterable<Client> client = clientRepository.findAll();
            Iterable<Product> product = productRepository.findAll();
            model.addAttribute("client", client);
            model.addAttribute("product", product);
            return "zakaz-add";
        }
        zakaz.setProduct(productRepository.findByNameProduct(nameProduct));
        zakaz.setClient(clientRepository.findByFirstNameClient(firstNameClient));
        zakazRepository.save(zakaz);
        return "redirect:/zakaz";
    }

    @GetMapping("/zakaz/{id}/edit")
    public  String zakazDetails(@PathVariable(value = "id") long id, Model model, Model models)
    {
        Iterable<Product> product = productRepository.findAll();
        Iterable<Client> client = clientRepository.findAll();
        models.addAttribute("product", product);
        models.addAttribute("client", client);
        Zakaz zakaz = zakazRepository.findById(id).orElseThrow();
        model.addAttribute("zakaz",zakaz);
        return "zakaz-edit";
    }

    @PostMapping ("/zakaz/{id}/edit")
    public  String zakazUpdate(@ModelAttribute("zakaz") @Valid Zakaz zakaz,
                               BindingResult bindingResult,
                               @PathVariable(value = "id") long id)
    {
        if(bindingResult.hasErrors())
            return "zakaz-edit";
        zakazRepository.save(zakaz);
        return "redirect:/zakaz";
    }

    @GetMapping("/zakaz/{id}/remove")
    public  String zakazDelete(@PathVariable(value = "id") long id, Model model)
    {
        Zakaz zakaz = zakazRepository.findById(id).orElseThrow();
        zakazRepository.delete(zakaz);
        return "redirect:/zakaz";
    }
}

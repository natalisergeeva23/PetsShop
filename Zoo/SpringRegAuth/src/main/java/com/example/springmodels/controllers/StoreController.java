package com.example.springmodels.controllers;

import com.example.springmodels.models.Product;
import com.example.springmodels.models.Store;
import com.example.springmodels.repository.ProductRepository;
import com.example.springmodels.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class StoreController {
    @Autowired
    private StoreRepository storeRepository;

    @GetMapping("/store")
    public String storeMain(Model model) {
        Iterable<Store> stores = storeRepository.findAll();
        model.addAttribute("stores", stores);
        return "store";
    }

    @GetMapping("/store/add")
    public String storeAdd(@ModelAttribute("stores") Store store) {
        return "store-add";
    }

    @PostMapping("/store/add")
    public String storePostAdd(@ModelAttribute("stores") @Valid Store store, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "store-add";
        }
        storeRepository.save(store);
        return "redirect:/store";
    }

    @GetMapping("/store/{id}/edit")
    public String storeDetails(@PathVariable(value = "id") long id, Model model) {
        Store store = storeRepository.findById(id).orElseThrow();
        model.addAttribute("store", store);
        return "store-edit";
    }

    @PostMapping ("/store/{id}/edit")
    public  String storeUpdate(@ModelAttribute("store") @Valid Store store,
                                 BindingResult bindingResult,
                                 @PathVariable(value = "id") long id)
    {
        if(bindingResult.hasErrors())
            return "store-edit";
        storeRepository.save(store);
        return "redirect:/store";
    }

    @GetMapping("/store/{id}/remove")
    public  String storeDelete(@PathVariable(value = "id") long id, Model model)
    {
        Store store = storeRepository.findById(id).orElseThrow();
        storeRepository.delete(store);
        return "redirect:/store";
    }
}

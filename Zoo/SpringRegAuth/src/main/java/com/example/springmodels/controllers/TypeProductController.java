package com.example.springmodels.controllers;

import com.example.springmodels.models.Product;
import com.example.springmodels.models.TypeProduct;
import com.example.springmodels.repository.TypeProductRepository;
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
@PreAuthorize("hasAnyAuthority('MANAGER','ADMIN')")
public class TypeProductController {
    @Autowired
    private TypeProductRepository typeProductRepository;

    @GetMapping("/typeProduct")
    public String typeProductMain(Model model) {
        Iterable<TypeProduct> typeProducts = typeProductRepository.findAll();
        model.addAttribute("typeProducts", typeProducts);
        return "typeProduct";
    }

    @GetMapping("/typeProduct/add")
    public String typeProductAdd(@ModelAttribute("typeProducts") TypeProduct typeProduct) {
        return "typeProduct-add";
    }

    @PostMapping("/typeProduct/add")
    public String productPostAdd(@ModelAttribute("typeProducts") @Valid TypeProduct typeProduct, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "typeProduct-add";
        }
        typeProductRepository.save(typeProduct);
        return "redirect:/typeProduct";
    }

    @GetMapping("/typeProduct/{id}/edit")
    public String typeProductDetails(@PathVariable(value = "id") long id, Model model) {
        TypeProduct typeProduct = typeProductRepository.findById(id).orElseThrow();
        model.addAttribute("typeProduct", typeProduct);
        return "typeProduct-edit";
    }

    @PostMapping ("/typeProduct/{id}/edit")
    public  String productUpdate(@ModelAttribute("typeProduct") @Valid TypeProduct typeProduct,
                                 BindingResult bindingResult,
                                 @PathVariable(value = "id") long id)
    {
        if(bindingResult.hasErrors())
            return "typeProduct-edit";
        typeProductRepository.save(typeProduct);
        return "redirect:/typeProduct";
    }

    @GetMapping("/typeProduct/{id}/remove")
    public  String typeProductDelete(@PathVariable(value = "id") long id, Model model)
    {
        TypeProduct typeProduct = typeProductRepository.findById(id).orElseThrow();
        typeProductRepository.delete(typeProduct);
        return "redirect:/typeProduct";
    }
}

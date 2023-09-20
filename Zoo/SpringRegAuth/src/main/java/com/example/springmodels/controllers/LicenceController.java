package com.example.springmodels.controllers;

import com.example.springmodels.models.Licence;
import com.example.springmodels.repository.LicenceRepository;
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
public class LicenceController {

    @Autowired
    private LicenceRepository licenceRepository;

    @GetMapping("/licence")
    public String licenceMain(Model model) {
        Iterable<Licence> licences = licenceRepository.findAll();
        model.addAttribute("licences", licences);
        return "licence";
    }

    @GetMapping("/licence/add")
    public String licenceAdd(@ModelAttribute("licences") Licence licence) {
        return "licence-add";
    }

    @PostMapping("/licence/add")
    public String licencePostAdd(@ModelAttribute("licences") @Valid Licence licence, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "licence-add";
        }
        licenceRepository.save(licence);
        return "redirect:/licence";
    }

    @GetMapping("/licence/{id}/edit")
    public  String licenceDetails(@PathVariable(value = "id") long id, Model model) {
        Licence licence = licenceRepository.findById(id).orElseThrow();
        model.addAttribute("licence",licence);
        return "licence-edit";
    }

    @PostMapping ("/licence/{id}/edit")
    public  String licenceUpdate(@ModelAttribute("product") @Valid Licence licence,
                                 BindingResult bindingResult,
                                 @PathVariable(value = "id") long id)
    {
        if(bindingResult.hasErrors())
            return "licence-edit";
        licenceRepository.save(licence);
        return "redirect:/licence";
    }

    @GetMapping("/licence/{id}/remove")
    public  String licenceDelete(@PathVariable(value = "id") long id, Model model)
    {
        Licence licence = licenceRepository.findById(id).orElseThrow();
        licenceRepository.delete(licence);
        return "redirect:/licence";
    }
}

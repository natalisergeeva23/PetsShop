package com.example.springmodels.controllers;

import com.example.springmodels.models.Employee;
import com.example.springmodels.models.EmployeePosition;
import com.example.springmodels.repository.EmployeePositionRepository;
import com.example.springmodels.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@PreAuthorize("hasAnyAuthority('MANAGER','ADMIN')")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeePositionRepository employeePositionRepository;

    @GetMapping("/employee")
    public String employeeMain(Model model) {
        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "employee";
    }

    @GetMapping("/employee/add")
    public String employeeAdd(@ModelAttribute("employees") Employee employee, Model position) {
        Iterable<EmployeePosition> employeePosition = employeePositionRepository.findAll();
        position.addAttribute("employeePosition",employeePosition);
        return "employee-add";
    }

    @PostMapping("/employee/add")
    public String employeePostAdd(@ModelAttribute("employees") @Valid Employee employee,
                                  BindingResult bindingResult, @RequestParam String nameEmployeePosition, Model position) {
        if(bindingResult.hasErrors()) {
            Iterable<EmployeePosition> employeePosition = employeePositionRepository.findAll();
            position.addAttribute("employeePosition", employeePosition);
            return "employee-add";
        }
        employee.setEmployeePosition(employeePositionRepository.findByNameEmployeePosition(nameEmployeePosition));
        employeeRepository.save(employee);
        return "redirect:/employee";
    }

    @GetMapping("/employee/{id}/edit")
    public  String employeeDetails(@PathVariable(value = "id") long id, Model model, Model position)
    {
        Iterable<EmployeePosition> employeePosition = employeePositionRepository.findAll();
        position.addAttribute("employeePosition",employeePosition);
        Employee employee = employeeRepository.findById(id).orElseThrow();
        model.addAttribute("employee",employee);
        return "employee-edit";
    }

    @PostMapping ("/employee/{id}/edit")
    public  String employeeUpdate(@ModelAttribute("employee") @Valid Employee employee,
                                  BindingResult bindingResult,
                                  @PathVariable(value = "id") long id)
    {
        if(bindingResult.hasErrors())
            return "employee-edit";
        employeeRepository.save(employee);
        return "redirect:/employee";
    }

    @GetMapping("/employee/{id}/remove")
    public  String employeeDelete(@PathVariable(value = "id") long id, Model model)
    {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employeeRepository.delete(employee);
        return "redirect:/employee";
    }
}

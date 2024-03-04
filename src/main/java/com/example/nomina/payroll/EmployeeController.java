package com.example.nomina.payroll;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeRepository repository;

    EmployeeController(EmployeeRepository repository){
        this.repository = repository;
    }


    @GetMapping("/employees")
    List<Employee> getAll (){
        return repository.findAll();
    }

    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee){
        return repository.save(newEmployee);
    }

    @GetMapping("/Employees/{id}" )
    Employee getOne(@PathVariable Long id){
        return repository.getReferenceById(id);
    }



}

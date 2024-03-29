package com.example.nomina.payroll;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class EmployeeController {

    private final EmployeeRepository repository;

    EmployeeController(EmployeeRepository repository){
        this.repository = repository;
    }


    @GetMapping("/employees")
    CollectionModel<EntityModel<Employee>> getAll (){
        List<EntityModel<Employee>> employees = repository.findAll().stream()
                .map(employee -> EntityModel.of(employee,
                        linkTo(methodOn(EmployeeController.class).getOne(employee.getId())).withSelfRel(),

                        linkTo(methodOn(EmployeeController.class).getAll()).withRel("employees")))
                .collect(Collectors.toList());

        return CollectionModel.of(employees,linkTo(methodOn(EmployeeController.class).getAll()).withSelfRel());
    }

    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee){
        return repository.save(newEmployee);
    }

    @GetMapping("/employees/{id}" )
    EntityModel<Employee> getOne(@PathVariable Long id){
        Employee employee = repository.findById(id)
                .orElseThrow(()-> new EmployeeNotFoundException(id));
        
        return EntityModel.of(employee,
                linkTo(methodOn(EmployeeController.class).getOne(id)).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).getAll()).withRel("employees"));
        
    }

    @PutMapping("/employees/{id}")
    Employee remplaceEmployee (@RequestBody Employee newEmployee, @PathVariable Long id){

        return repository.findById(id).map(employee -> {
            employee.setName(newEmployee.getName());
            employee.setRoll(newEmployee.getRoll());
            return repository.save(employee);
        }).orElseGet(() ->{
            newEmployee.setId(id);
            return repository.save(newEmployee);
        });
    }

    @DeleteMapping("/employee/{id}")
    void deleteEmployee(@PathVariable Long id){
        repository.deleteById(id);
    }



}

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

    @GetMapping("/employees/{id}" )
    Employee getOne(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(()-> new EmployeeNotFoundException(id));
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

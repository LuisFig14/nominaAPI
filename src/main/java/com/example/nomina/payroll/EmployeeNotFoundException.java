package com.example.nomina.payroll;

class EmployeeNotFoundException extends RuntimeException {

    EmployeeNotFoundException(Long id){
        super("Empleado no encontrado con el id: " + id);
    }

}

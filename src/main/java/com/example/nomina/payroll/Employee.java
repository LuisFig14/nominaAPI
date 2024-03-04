package com.example.nomina.payroll;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Employee {

//attributes

    private @Id @GeneratedValue Long id;
    private String name;
    private String roll;

//constructors

    Employee(){

    }

    Employee(String name, String roll){
        this.name = name;
        this.roll = roll;
    }

//Getters

    public Long getId(){
       return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getRoll(){
        return this.roll;
    }

//Setters
    public void setId(Long id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setRoll(String roll){
        this.roll = roll;
    }
    @Override
    public boolean equals(Object o){

        if(this == o)
            return true;
        if (!(o instanceof Employee))
            return false;

        Employee employee = (Employee) o;
        return Objects.equals(this.id, employee.id) && Objects.equals(this.name, employee.name)
                && Objects.equals(this.roll, employee.name);

    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.name, this.roll);
    }

    @Override
    public String toString(){
        return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' + ", role='" + this.roll + '\'' + '}';
    }








}

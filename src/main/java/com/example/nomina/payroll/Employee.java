package com.example.nomina.payroll;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

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

//Getters and setters

    public Long getId(){
       return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getRoll(){
        return this.roll;
    }


    public void setId(Long id){
        this.id = id;
    }







}

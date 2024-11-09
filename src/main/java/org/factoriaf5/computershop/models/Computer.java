package org.factoriaf5.computershop.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "computers")
public class Computer {
   

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String memory;
    private String processor;
    private String operatingsystem;
    private double price;
    // Atributos
    

    // Constructor
    public Computer (Long id, String brand, String memory, String processor, String operatingsystem, double price) {
        this.id = id;
        this.brand = brand;
        this.processor = processor;
        this.operatingsystem = operatingsystem;
        this.price = price;
    }

    // Métodos getter y setter
    public String getbrand() {
        return brand;
    }

    public void setbrand(String brand) {
        this.brand = brand;
    }

    public String getmemory() {
        return memory;
    }

    public void setmemory(String memory) {
        this.memory = memory;
    }

    public String getprocessor() {
        return processor;
    }

    public void setprocessor(String processor) {
        this.processor = processor;
    }

    public String getoperatingsystem() {
        return operatingsystem;
    }

    public void setoperatingsystem(String operatingsystem) {
        this.operatingsystem = operatingsystem;
    }

    public double getPrecio() {
        return price;
    }

    public void setPrecio(double price) {
        this.price = price;
    }

    // Método para mostrar la información de la computadora
    @Override
    public String toString() {
        return "Computer [Marca=" + brand + ", Memoria=" + memory + "GB, Procesador=" + processor
                + ", SO=" + operatingsystem + ", Precio=" + price + "]";
    }
}




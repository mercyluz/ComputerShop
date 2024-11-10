package org.factoriaf5.computershop.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "computer")
public class Computer {
   

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_computer")
    private Long id;
    private String brand;
    private String memory;
    private String processor;
    private String operatingsystem;
    private double price;
    // Atributos
    

    // Constructor
    public Computer (Long id, String brand, int memory, String processor, String  operatingsystem, Double price) {
        this.id = id;
        this.brand = brand;
        this.processor = processor;
        this.operatingsystem = operatingsystem;
        this.price = price;
    }
  

    public Computer(long id2, String brand2, String string, String processor2, String operatingsystem2, double price2) {
        //TODO Auto-generated constructor stub
    }


    // Métodos getter y setter
     // Métodos getter y setter
     public String getbrand() {
        return brand;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
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

    public Double getprice() {
        return price;
    }

    public void setprice(Double price) {
        this.price = price;
    }

    // Método para mostrar la información de la computadora
    @Override
    public String toString() {
        return "Computer [Marca=" + brand + ", Memoria=" + memory + "GB, Procesador=" + processor
                + ", SO=" + operatingsystem + ", Precio=" + price + "]";
    }
}




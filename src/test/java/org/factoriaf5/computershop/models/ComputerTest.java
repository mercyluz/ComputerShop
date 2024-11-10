package org.factoriaf5.computershop.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ComputerTest {

    private Computer computer;

    // Este método se ejecuta antes de cada prueba, y se asegura de que cada prueba tenga una nueva instancia de la clase Computer
    @BeforeEach
    public void setUp() {
        // Inicializamos el objeto Computer
        computer = new Computer(1L, "Dell", "16", "Intel i7", "Windows 10", 1200.00);
    }

    // Probamos el constructor y los métodos getter
    @Test
    void testGetId(){
        computer.setId(1L);
        assertEquals(1L, computer.getId());
    }
 
    // Probamos los métodos setters
    @Test
    public void testSetters() {
        computer.setbrand("HP");
        computer.setmemory("32");
        computer.setprocessor("Intel i9");
        computer.setoperatingsystem("Linux");
        computer.setprice(1500.00);

        // Verificamos que los valores hayan sido actualizados
        assertEquals("HP", computer.getbrand());
        assertEquals("32", computer.getmemory());
        assertEquals("Intel i9", computer.getprocessor());
        assertEquals("Linux", computer.getoperatingsystem());
        assertEquals(1500.00, computer.getprice());
    }

    }

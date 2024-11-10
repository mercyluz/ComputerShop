package org.factoriaf5.computershop.controllers;

import org.factoriaf5.computershop.models.Computer;
import org.factoriaf5.computershop.services.ComputerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ComputerControllerTest {

    @Mock
    private ComputerService computerService;

    @InjectMocks
    private ComputerController computerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddComputer() {
        // Arrange: Crear un objeto Computer y simular la respuesta del servicio
        Computer computer = new Computer(1L, "Dell", 512, "XRT", "Windows 7", (double) 1505);
        when(computerService.addComputer(any(Computer.class))).thenReturn(computer);

        // Act: Llamar al método del controlador
        ResponseEntity<Computer> response = computerController.addComputer(computer);

        // Assert: Verificar que el código de estado sea CREATED (201)
        assertEquals(HttpStatus.CREATED, response.getStatusCode(), "El código de estado debe ser 201 (CREATED)");

        // Verificar que el cuerpo de la respuesta contiene los datos del computador
        assertNotNull(response.getBody(), "El cuerpo de la respuesta no debe ser nulo");
        assertEquals("Dell", response.getBody().getbrand(), "La marca debe ser Dell");
        assertEquals(512, response.getBody().getmemory(), "La memoria debe ser 512 GB");
        assertEquals("XRT", response.getBody().getprocessor(), "El procesador debe ser XRT");
        assertEquals("Windows 7", response.getBody().getoperatingsystem(), "El sistema operativo debe ser Windows 7");
        assertEquals(1505, response.getBody().getprice(), "El precio debe ser 1505");

        // Verificar que el servicio fue llamado una vez
        verify(computerService, times(1)).addComputer(any(Computer.class));
    }

    @Test
    void testDeleteComputerByBrand() {
        // Arrange: Simular que el servicio elimina un computador
        when(computerService.deleteComputerByBrand("Dell")).thenReturn(true);

        // Act: Llamar al método del controlador
        ResponseEntity<String> response = computerController.deleteComputerByBrand("Dell");

        // Assert: Verificar que la respuesta sea OK (200) y el mensaje correcto
        assertEquals(HttpStatus.OK, response.getStatusCode(), "El código de estado debe ser 200 (OK)");
        assertEquals("Computador(s) eliminado(s) con marca Dell", response.getBody(), "El mensaje debe ser correcto");

        // Verificar que el servicio fue llamado
        verify(computerService, times(1)).deleteComputerByBrand("Dell");
    }

    @Test
    void testDeleteComputerByBrandNotFound() {
        // Arrange: Simular que no se encuentran computadores con la marca
        when(computerService.deleteComputerByBrand("Dell")).thenReturn(false);

        // Act: Llamar al método del controlador
        ResponseEntity<String> response = computerController.deleteComputerByBrand("Dell");

        // Assert: Verificar que la respuesta sea NOT_FOUND (404) y el mensaje correcto
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "El código de estado debe ser 404 (NOT_FOUND)");
        assertEquals("No se encontraron computadores con la marca Dell", response.getBody(), "El mensaje debe ser correcto");

        // Verificar que el servicio fue llamado
        verify(computerService, times(1)).deleteComputerByBrand("Dell");
    }

    @Test
    void testGetComputersByBrand() {
        // Arrange: Simular la respuesta del servicio con una lista de computadores
        Computer computer1 = new Computer(1L, "Dell", 512, "XRT", "Windows 7", (double) 1505);
        Computer computer2 = new Computer(2L, "Dell", 1024, "Intel", "Windows 10", (double) 2000);
        when(computerService.findComputersByBrand("Dell")).thenReturn(Arrays.asList(computer1, computer2));

        // Act: Llamar al método del controlador
        ResponseEntity<List<Computer>> response = computerController.getComputersByBrand("Dell");

        // Assert: Verificar que la respuesta sea OK (200) y que contenga los computadores correctos
        assertEquals(HttpStatus.OK, response.getStatusCode(), "El código de estado debe ser 200 (OK)");
        assertNotNull(response.getBody(), "El cuerpo de la respuesta no debe ser nulo");
        assertEquals(2, response.getBody().size(), "Debe haber dos computadores con la marca Dell");

        // Verificar que el servicio fue llamado
        verify(computerService, times(1)).findComputersByBrand("Dell");
    }

    @Test
    void testGetComputersByBrandNotFound() {
        // Arrange: Simular que no se encuentran computadores con la marca
        when(computerService.findComputersByBrand("HP")).thenReturn(Arrays.asList());

        // Act: Llamar al método del controlador
        ResponseEntity<List<Computer>> response = computerController.getComputersByBrand("HP");

        // Assert: Verificar que la respuesta sea NOT_FOUND (404)
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "El código de estado debe ser 404 (NOT_FOUND)");
        assertTrue(response.getBody().isEmpty(), "El cuerpo de la respuesta debe estar vacío");

        // Verificar que el servicio fue llamado
        verify(computerService, times(1)).findComputersByBrand("HP");
    }

    @Test
    void testGetAllComputers() {
        // Arrange: Simular la respuesta del servicio con una lista de computadores
        Computer computer1 = new Computer(1L, "Dell", 512, "XRT", "Windows 7", (double) 1505);
        Computer computer2 = new Computer(2L, "HP", 1024, "Intel", "Windows 10", (double) 2000);
        when(computerService.getAllComputers()).thenReturn(Arrays.asList(computer1, computer2));

        // Act: Llamar al método del controlador
        ResponseEntity<List<Computer>> response = computerController.getAllComputers();

        // Assert: Verificar que la respuesta sea OK (200) y que contenga los computadores correctos
        assertEquals(HttpStatus.OK, response.getStatusCode(), "El código de estado debe ser 200 (OK)");
        assertNotNull(response.getBody(), "El cuerpo de la respuesta no debe ser nulo");
        assertEquals(2, response.getBody().size(), "Debe haber dos computadores");

        // Verificar que el servicio fue llamado
        verify(computerService, times(1)).getAllComputers();
    }

    @Test
    void testGetComputerById() {
        // Arrange: Simular la respuesta del servicio con un computador
        Computer computer = new Computer(1L, "Dell", 512, "XRT", "Windows 7", (double) 1505);
        when(computerService.getComputerById(1L)).thenReturn(Optional.of(computer));

        // Act: Llamar al método del controlador
        ResponseEntity<Computer> response = computerController.getComputerById(1L);

        // Assert: Verificar que la respuesta sea OK (200) y que el computador es correcto
        assertEquals(HttpStatus.OK, response.getStatusCode(), "El código de estado debe ser 200 (OK)");
        assertNotNull(response.getBody(), "El cuerpo de la respuesta no debe ser nulo");
        assertEquals("Dell", response.getBody().getbrand(), "La marca debe ser Dell");
        assertEquals(null, response.getBody().getmemory(), "La memoria debe ser 512 GB");

        // Verificar que el servicio fue llamado
        verify(computerService, times(1)).getComputerById(1L);
    }


    @Test
    void testGetComputerByIdNotFound() {
        // Arrange: Simular que no se encuentra el computador
        when(computerService.getComputerById(1L)).thenReturn(Optional.empty());

        // Act: Llamar al método del controlador
        ResponseEntity<Computer> response = computerController.getComputerById(1L);

        // Assert: Verificar que la respuesta sea NOT_FOUND (404)
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "El código de estado debe ser 404 (NOT_FOUND)");
        assertNull(response.getBody(), "El cuerpo de la respuesta debe ser nulo");

        // Verificar que el servicio fue llamado
        verify(computerService, times(1)).getComputerById(1L);
    }
}

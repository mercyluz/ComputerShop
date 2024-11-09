package org.factoriaf5.computershop.controllers;

import org.factoriaf5.computershop.models.Computer;
import org.factoriaf5.computershop.services.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/computers")
public class ComputerController {

    @Autowired
    private ComputerService computerService;

    // Endpoint para agregar un computador
    @PostMapping
    public ResponseEntity<Computer> addComputer(@RequestBody Computer computer) {
        Computer savedComputer = computerService.addComputer(computer);
        return new ResponseEntity<>(savedComputer, HttpStatus.CREATED);
    }

    // Endpoint para eliminar un computador por marca
    @DeleteMapping("/brand/{brand}")
    public ResponseEntity<String> deleteComputerByBrand(@PathVariable String brand) {
        boolean deleted = computerService.deleteComputerByBrand(brand);
        if (deleted) {
            return new ResponseEntity<>("Computador(s) eliminado(s) con marca " + brand, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontraron computadores con la marca " + brand, HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para buscar computadores por marca
    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Computer>> getComputersByBrand(@PathVariable String brand) {
        List<Computer> computers = computerService.findComputersByBrand(brand);
        if (computers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(computers, HttpStatus.OK);
    }

    // Endpoint para listar todos los computadores
    @GetMapping
    public ResponseEntity<List<Computer>> getAllComputers() {
        List<Computer> computers = computerService.getAllComputers();
        return new ResponseEntity<>(computers, HttpStatus.OK);
    }

    // Endpoint para obtener un computador por ID
    @GetMapping("/{id}")
    public ResponseEntity<Computer> getComputerById(@PathVariable Long id) {
        Optional<Computer> computer = computerService.getComputerById(id);
        if (computer.isPresent()) {
            return new ResponseEntity<>(computer.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

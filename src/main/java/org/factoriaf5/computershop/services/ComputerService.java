package org.factoriaf5.computershop.services;

import org.factoriaf5.computershop.models.Computer;
import org.factoriaf5.computershop.repositories.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComputerService {

    @Autowired
    private ComputerRepository computerRepository;

    // Agregar un computador
    public Computer addComputer(Computer computer) {
        return computerRepository.save(computer);
    }

    // Eliminar un computador por su marca
    public boolean deleteComputerByBrand(String brand) {
        List<Computer> computers = computerRepository.findByBrand(brand);
        if (!computers.isEmpty()) {
            computerRepository.deleteByBrand(brand);
            return true;
        }
        return false;
    }

    // Buscar un computador por marca
    public List<Computer> findComputersByBrand(String brand) {
        return computerRepository.findByBrand(brand);
    }

    // Listar todos los computadores
    public List<Computer> getAllComputers() {
        return computerRepository.findAll();
    }

    // Buscar computador por ID
    public Optional<Computer> getComputerById(Long id) {
        return computerRepository.findById(id);
    }
}

package org.factoriaf5.computershop.repositories;


    import org.factoriaf5.computershop.models.Computer;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;
    
    import java.util.List;
    
    @Repository
    public interface ComputerRepository extends JpaRepository<Computer, Long> {
    
        // Buscar computadores por marca
        List<Computer> findByBrand(String brand);
    
        // Eliminar computadores por marca
        void deleteByBrand(String brand);
    }


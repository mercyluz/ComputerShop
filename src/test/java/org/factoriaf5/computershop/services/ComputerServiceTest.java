package org.factoriaf5.computershop.services;

import org.factoriaf5.computershop.models.Computer;
import org.factoriaf5.computershop.repositories.ComputerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ComputerServiceTest {

    @Mock
    private ComputerRepository computerRepository;

    @InjectMocks
    private ComputerService computerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddComputer() {
        // Arrange: Crear un computador y simular que el repositorio guarda correctamente
        Computer computer = new Computer(1L, "Dell", 512, "XRT", "Windows 7", (double) 1505);
        when(computerRepository.save(any(Computer.class))).thenReturn(computer);

        // Act: Llamar al método para agregar el computador
        Computer result = computerService.addComputer(computer);

        // Assert: Verificar que el computador ha sido agregado correctamente
        assertNotNull(result, "El computador no debe ser nulo");
        assertEquals("Dell", result.getbrand(), "La marca del computador debe ser Dell");
        assertEquals(1505, result.getprice(), "El precio del computador debe ser 1505");

        // Verificar que el repositorio fue llamado una vez
        verify(computerRepository, times(1)).save(any(Computer.class));
    }

    @Test
    void testDeleteComputerByBrand() {
        // Arrange: Simular que el repositorio encuentra y elimina computadores con la marca "Dell"
        when(computerRepository.findByBrand("Dell")).thenReturn(Arrays.asList(new Computer(1L, "Dell", 512, "Intel", "Windows 10", (double) 1200)));
        doNothing().when(computerRepository).deleteByBrand("Dell");

        // Act: Llamar al método para eliminar computadores por marca
        boolean result = computerService.deleteComputerByBrand("Dell");

        // Assert: Verificar que la eliminación fue exitosa
        assertTrue(result, "Debe eliminar los computadores con la marca Dell");

        // Verificar que el repositorio fue llamado una vez para encontrar los computadores y eliminarlos
        verify(computerRepository, times(1)).findByBrand("Dell");
        verify(computerRepository, times(1)).deleteByBrand("Dell");
    }

    @Test
    void testDeleteComputerByBrandNotFound() {
        // Arrange: Simular que no se encuentran computadores con la marca "HP"
        when(computerRepository.findByBrand("HP")).thenReturn(Arrays.asList());

        // Act: Llamar al método para eliminar computadores por marca
        boolean result = computerService.deleteComputerByBrand("HP");

        // Assert: Verificar que no se eliminó ningún computador
        assertFalse(result, "No debe eliminar ningún computador porque no hay computadores con la marca HP");

        // Verificar que el repositorio fue llamado para buscar la marca
        verify(computerRepository, times(1)).findByBrand("HP");
    }

    @Test
    void testFindComputersByBrand() {
        // Arrange: Simular la respuesta del repositorio con una lista de computadores
        List<Computer> computers = Arrays.asList(
                new Computer(1L, "Dell", 512, "Intel", "Windows 7", (double) 1500),
                new Computer(2L, "Dell", 1024, "AMD", "Windows 10", (double) 1800)
        );
        when(computerRepository.findByBrand("Dell")).thenReturn(computers);

        // Act: Llamar al método para buscar computadores por marca
        List<Computer> result = computerService.findComputersByBrand("Dell");

        // Assert: Verificar que la lista contiene los computadores correctos
        assertNotNull(result, "La lista de computadores no debe ser nula");
        assertEquals(2, result.size(), "Debe haber 2 computadores con la marca Dell");
        assertEquals("Dell", result.get(0).getbrand(), "La primera computadora debe ser de la marca Dell");

        // Verificar que el repositorio fue llamado una vez
        verify(computerRepository, times(1)).findByBrand("Dell");
    }

    @Test
    void testGetAllComputers() {
        // Arrange: Simular que el repositorio devuelve una lista de computadores
        List<Computer> computers = Arrays.asList(
                new Computer(1L, "Dell", 512, "Intel", "Windows 7", (double) 1500),
                new Computer(2L, "HP", 1024, "AMD", "Windows 10", (double) 1800)
        );
        when(computerRepository.findAll()).thenReturn(computers);

        // Act: Llamar al método para obtener todos los computadores
        List<Computer> result = computerService.getAllComputers();

        // Assert: Verificar que la lista de computadores es correcta
        assertNotNull(result, "La lista de computadores no debe ser nula");
        assertEquals(2, result.size(), "Debe haber 2 computadores en total");

        // Verificar que el repositorio fue llamado una vez
        verify(computerRepository, times(1)).findAll();
    }

    @Test
    void testGetComputerById() {
        // Arrange: Simular la respuesta del repositorio con un computador
        Computer computer = new Computer(1L, "Dell", 512, "Intel", "Windows 7", (double) 1500);
        when(computerRepository.findById(1L)).thenReturn(Optional.of(computer));

        // Act: Llamar al método para obtener un computador por ID
        Optional<Computer> result = computerService.getComputerById(1L);

        // Assert: Verificar que el computador devuelto es el correcto
        assertTrue(result.isPresent(), "El computador debe ser encontrado");
        assertEquals("Dell", result.get().getbrand(), "La marca debe ser Dell");

        // Verificar que el repositorio fue llamado una vez
        verify(computerRepository, times(1)).findById(1L);
    }
}

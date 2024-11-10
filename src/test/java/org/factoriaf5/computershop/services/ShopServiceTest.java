package org.factoriaf5.computershop.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.factoriaf5.computershop.models.Shop;
import org.factoriaf5.computershop.repositories.ShopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ShopServiceTest {

    @Mock
    private ShopRepository shopRepository;

    @InjectMocks
    private ShopService shopService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
      
    }

    @Test
    void testCreateShop() {
        // Arrange: Crear una tienda para guardar
        Shop shop = new Shop(1L, "Tienda F5", "Carlos Perez", "ABC123");

        // Simular que el repositorio guarda la tienda
        when(shopRepository.save(shop)).thenReturn(shop);

        // Act: Llamar al método de servicio
        Shop createdShop = shopService.createShop(shop);

        // Assert: Verificar que la tienda fue creada correctamente
        assertNotNull(createdShop);
        assertEquals("Tienda F5", createdShop.getName());
        assertEquals("Carlos Perez", createdShop.getOwner());
        assertEquals("ABC123", createdShop.getTaxid());

        // Verificar que el método `save` del repositorio fue llamado una vez
        verify(shopRepository, times(1)).save(shop);
    }

    @Test
    void testCreateShop_Invalid() {
        // Arrange: Crear una tienda con datos inválidos (por ejemplo, campos vacíos)
        Shop invalidShop = new Shop(1L, "", "", "");

        // Simular que el repositorio no guarda la tienda (suponiendo que se valide en el servicio)
        when(shopRepository.save(invalidShop)).thenReturn(null);

        // Act: Llamar al método de servicio
        Shop createdShop = shopService.createShop(invalidShop);

        // Assert: Verificar que la tienda no fue creada
        assertNull(createdShop, "La tienda no debe ser creada si es inválida");
    }

   
    @Test
    void testGetShopById_Found() {
        Shop shop = new Shop(1L, "Tienda F5", "Carlos Perez", "ABC123");
        when(shopRepository.findById(1L)).thenReturn(Optional.of(shop));

        Optional<Shop> foundShop = shopService.getShopById(1L);

        assertTrue(foundShop.isPresent());
        assertEquals("Tienda F5", foundShop.get().getName());

        verify(shopRepository, times(1)).findById(1L);
    }

    @Test
    void testGetShopById_NotFound() {
        when(shopRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Shop> foundShop = shopService.getShopById(1L);

        assertFalse(foundShop.isPresent());

        verify(shopRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllShops() {
        Shop shop1 = new Shop(1L, "Tienda F5", "Carlos Perez", "ABC123");
        Shop shop2 = new Shop(2L, "Tienda G5", "Juan Martinez", "XYZ456");

        List<Shop> shops = Arrays.asList(shop1, shop2);
        when(shopRepository.findAll()).thenReturn(shops);

        Iterable<Shop> allShops = shopService.getAllShops();

        assertNotNull(allShops);
        assertTrue(allShops.spliterator().getExactSizeIfKnown() > 0);

        verify(shopRepository, times(1)).findAll();
    }

    @Test
    void testGetAllShops_EmptyList() {
        when(shopRepository.findAll()).thenReturn(Arrays.asList());

        Iterable<Shop> allShops = shopService.getAllShops();

        assertNotNull(allShops);
        assertFalse(allShops.iterator().hasNext());

        verify(shopRepository, times(1)).findAll();
    }

    @Test
    void testUpdateShop_Success() {
        Shop shop = new Shop(1L, "Tienda F5", "Carlos Perez", "ABC123");
        Shop updatedShop = new Shop(1L, "Tienda F5 Actualizada", "Carlos Perez", "DEF789");
        when(shopRepository.existsById(1L)).thenReturn(true);
        when(shopRepository.save(updatedShop)).thenReturn(updatedShop);

        Shop result = shopService.updateShop(1L, updatedShop);

        assertNotNull(result);
        assertEquals("Tienda F5 Actualizada", result.getName());

        verify(shopRepository, times(1)).existsById(1L);
        verify(shopRepository, times(1)).save(updatedShop);
    }

    @Test
    void testUpdateShop_Invalid() {
        Shop invalidShop = new Shop(1L, "", "", "");
        when(shopRepository.existsById(1L)).thenReturn(true);
        when(shopRepository.save(invalidShop)).thenReturn(null);

        Shop updatedShop = shopService.updateShop(1L, invalidShop);

        assertNull(updatedShop);
    }

    @Test
    void testDeleteShop_Success() {
        when(shopRepository.existsById(1L)).thenReturn(true);

        boolean deleted = shopService.deleteShop(1L);

        assertTrue(deleted);
        verify(shopRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteShop_NotFound() {
        when(shopRepository.existsById(1L)).thenReturn(false);

        boolean deleted = shopService.deleteShop(1L);

        assertFalse(deleted);
        verify(shopRepository, times(1)).existsById(1L);
    }

    @Test
    void testDeleteShop_Success_VerifyDeletion() {
        when(shopRepository.existsById(1L)).thenReturn(true);

        boolean deleted = shopService.deleteShop(1L);

        assertTrue(deleted);

        verify(shopRepository, times(1)).deleteById(1L);
    }
}

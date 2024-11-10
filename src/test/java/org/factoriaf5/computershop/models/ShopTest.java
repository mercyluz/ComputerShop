package org.factoriaf5.computershop.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShopTest {

    @Test
    void testConstructor() {
        Shop shop = new Shop(1L, "TechStore", "John Doe", "123456789");

        // Verificar que el constructor asigna los valores correctamente
        assertEquals(1L, shop.getId());
        assertEquals("TechStore", shop.getName());
        assertEquals("John Doe", shop.getOwner());
        assertEquals("123456789", shop.getTaxid());
    }

    @Test
    void testGettersAndSetters() {
        Shop shop = new Shop(1L, "TechStore", "John Doe", "123456789");

        // Probar los métodos getters y setters
        shop.setName("SuperTech");
        assertEquals("SuperTech", shop.getName());

        shop.setOwner("Jane Doe");
        assertEquals("Jane Doe", shop.getOwner());

        shop.setTaxid("987654321");
        assertEquals("987654321", shop.getTaxid());
    }

    @Test
    void testSettersWithNullValues() {
        Shop shop = new Shop(1L, "TechStore", "John Doe", "123456789");

        // Establecer valores nulos y verificar que se asignan correctamente
        shop.setName(null);
        assertNull(shop.getName());

        shop.setOwner(null);
        assertNull(shop.getOwner());

        shop.setTaxid(null);
        assertNull(shop.getTaxid());
    }

    @Test
    void testIdSetterAndGetter() {
        Shop shop = new Shop(1L, "TechStore", "John Doe", "123456789");

        // Verificar el comportamiento del id
        assertEquals(1L, shop.getId());

        // Cambiar el ID y verificar si se actualiza correctamente
        shop.setId(2L);
        assertEquals(2L, shop.getId());
    }
}

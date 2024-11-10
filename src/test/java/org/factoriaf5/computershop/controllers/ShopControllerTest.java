package org.factoriaf5.computershop.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.factoriaf5.computershop.models.Shop;
import org.factoriaf5.computershop.services.ShopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@WebMvcTest(ShopController.class)  // Carga solo el controlador para las pruebas
class ShopControllerTest {

    @Autowired
    private MockMvc mockMvc;  // Simula las solicitudes HTTP

    @MockBean
    private ShopService shopService;  // Simula el servicio

    @Autowired
    private ObjectMapper objectMapper;  // Convierte objetos a JSON

    @Test
    void testCreateShop() throws Exception {
        // Arrange: Crear un objeto Shop y definir el comportamiento del mock
        Shop shop = new Shop(1L, "TechStore", "John Doe", "123456789");
        when(shopService.createShop(any(Shop.class))).thenReturn(shop);

        // Act: Realizar la solicitud POST
        var result = mockMvc.perform(post("/shops")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(shop)))
                .andReturn();  // Captura el resultado

        // Assert: Verificar que el estado y el contenido de la respuesta sean correctos
        assertEquals(201, result.getResponse().getStatus());
        assertTrue(result.getResponse().getContentAsString().contains("\"id\":1"));
        assertTrue(result.getResponse().getContentAsString().contains("\"name\":\"TechStore\""));
    }

    @Test
    void testGetShopById() throws Exception {
        // Arrange: Crear un objeto Shop con ID 1
        Shop shop = new Shop(1L, "TechStore", "John Doe", "123456789");
        when(shopService.getShopById(1L)).thenReturn(Optional.of(shop));

        // Act: Realizar la solicitud GET
        var result = mockMvc.perform(get("/shops/{id}", 1L))
                .andReturn();  // Captura el resultado

        // Assert: Verificar que el estado y el contenido de la respuesta sean correctos
        assertEquals(200, result.getResponse().getStatus());
        assertTrue(result.getResponse().getContentAsString().contains("\"id\":1"));
        assertTrue(result.getResponse().getContentAsString().contains("\"name\":\"TechStore\""));
    }

    @Test
    void testGetShopByIdNotFound() throws Exception {
        // Arrange: Simular que no se encuentra una tienda con el ID 999
        when(shopService.getShopById(999L)).thenReturn(Optional.empty());

        // Act: Realizar la solicitud GET
        var result = mockMvc.perform(get("/shops/{id}", 999L))
                .andReturn();  // Captura el resultado

        // Assert: Verificar que la respuesta sea 404 Not Found
        assertEquals(404, result.getResponse().getStatus());
    }

    @Test
    void testUpdateShop() throws Exception {
        // Arrange: Crear un objeto Shop con ID 1
        Shop existingShop = new Shop(1L, "TechStore", "John Doe", "123456789");
        Shop updatedShop = new Shop(1L, "TechStore Updated", "John Doe", "987654321");

        when(shopService.getShopById(1L)).thenReturn(Optional.of(existingShop));
        when(shopService.updateShop(1L, updatedShop)).thenReturn(updatedShop);

        // Act: Realizar la solicitud PUT
        var result = mockMvc.perform(put("/shops/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedShop)))
                .andReturn();  // Captura el resultado

        // Assert: Verificar que el estado y el contenido de la respuesta sean correctos
        assertEquals(200, result.getResponse().getStatus());
        assertTrue(result.getResponse().getContentAsString().contains("\"name\":\"TechStore Updated\""));
    }

    @Test
    void testDeleteShop() throws Exception {
        // Arrange: Simular que una tienda con ID 1 se elimina correctamente
        when(shopService.deleteShop(1L)).thenReturn(true);

        // Act: Realizar la solicitud DELETE
        var result = mockMvc.perform(delete("/shops/{id}", 1L))
                .andReturn();  // Captura el resultado

        // Assert: Verificar que el estado de la respuesta sea 200 OK
        assertEquals(200, result.getResponse().getStatus());
        assertTrue(result.getResponse().getContentAsString().contains("Tienda eliminada con éxito."));
    }

    @Test
    void testDeleteShopNotFound() throws Exception {
        // Arrange: Simular que no se encuentra una tienda con el ID 999
        when(shopService.deleteShop(999L)).thenReturn(false);

        // Act: Realizar la solicitud DELETE
        var result = mockMvc.perform(delete("/shops/{id}", 999L))
                .andReturn();  // Captura el resultado

        // Assert: Verificar que la respuesta sea 404 Not Found
        assertEquals(404, result.getResponse().getStatus());
        assertTrue(result.getResponse().getContentAsString().contains("No se encontró la tienda."));
    }
}


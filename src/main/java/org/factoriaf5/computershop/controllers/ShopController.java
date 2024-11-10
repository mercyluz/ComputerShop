package org.factoriaf5.computershop.controllers;

import org.factoriaf5.computershop.models.Shop;
import org.factoriaf5.computershop.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/shops")
public class ShopController {

    @Autowired
    private ShopService shopService;

    // Endpoint para crear una tienda
    @PostMapping
    public ResponseEntity<Shop> createShop(@RequestBody Shop shop) {
        Shop createdShop = shopService.createShop(shop);
        return new ResponseEntity<>(createdShop, HttpStatus.CREATED);
    }

    // Endpoint para obtener todos los datos de una tienda
    @GetMapping("/{id}")
    public ResponseEntity<Shop> getShopById(@PathVariable Long id) {
        Optional<Shop> shop = shopService.getShopById(id);
        if (shop.isPresent()) {
            return new ResponseEntity<>(shop.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para listar todas las tiendas
    @GetMapping
    public ResponseEntity<Iterable<Shop>> getAllShops() {
        Iterable<Shop> shops = shopService.getAllShops();
        return new ResponseEntity<>(shops, HttpStatus.OK);
    }

    // Endpoint para actualizar una tienda
    @PutMapping("/{id}")
    public ResponseEntity<Shop> updateShop(@PathVariable Long id, @RequestBody Shop shop) {
        Optional<Shop> existingShop = shopService.getShopById(id);
        if (existingShop.isPresent()) {
            Shop updatedShop = shopService.updateShop(id, shop);
            return new ResponseEntity<>(updatedShop, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para eliminar una tienda
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShop(@PathVariable Long id) {
        boolean deleted = shopService.deleteShop(id);
        if (deleted) {
            return new ResponseEntity<>("Tienda eliminada con éxito.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontró la tienda.", HttpStatus.NOT_FOUND);
        }

       
        }
    }


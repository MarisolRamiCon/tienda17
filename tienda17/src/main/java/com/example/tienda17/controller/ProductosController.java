package com.example.tienda17.controller;

import com.example.tienda17.entity.Productos;
import com.example.tienda17.service.impl.ProductosService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tienda")
public class ProductosController {
    private final ProductosService productosservice;
    public ProductosController(ProductosService productosservice) {
        this.productosservice = productosservice;
    }
    @GetMapping("/productos")
    public List<Productos> readAll(){
        return productosservice.ReadAll();
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<?> getProductoById(@PathVariable Integer id) {
        Optional<Productos> producto = productosservice.ReadById(id);
        if (producto.isPresent()) {
            return ResponseEntity.ok(producto.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el ID");
        }
    }

    @PostMapping("/crearprod")
    public ResponseEntity<?> create(@RequestBody Productos productos){
        if (productos.getProdnombre() == null || productos.getProdnombre().trim().isEmpty() ||
            productos.getProddescripcion() == null || productos.getProddescripcion().trim().isEmpty() ||
            productos.getProdcategoria() == null || productos.getProdcategoria().trim().isEmpty() ||
            productos.getProdprecio() == 0.0 ||
            productos.getIdproveedor() == null ||
            productos.getProdstock() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Todos los campos son obligatorios");
        }
        return ResponseEntity.ok(productosservice.create(productos));
    }

    @PutMapping("/actualizaprod")
    public ResponseEntity<?> update(@RequestBody Productos productos){
        if (productos.getProdnombre() == null || productos.getProdnombre().trim().isEmpty() ||
            productos.getProddescripcion() == null || productos.getProddescripcion().trim().isEmpty() ||
            productos.getProdcategoria() == null || productos.getProdcategoria().trim().isEmpty() ||
            productos.getProdprecio() == 0.0 ||
            productos.getIdproveedor() == null ||
            productos.getProdstock() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Todos los campos son obligatorios.");
        } else {
            return ResponseEntity.ok(productosservice.update(productos));
        }
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        String resultado = productosservice.deleteById(id);
        if (resultado.equalsIgnoreCase("Producto eliminado correctamente")) {
            return ResponseEntity.ok(resultado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el producto con el ID proporcionado");
        }
    }

    @GetMapping("/precioMayor")
    public ResponseEntity<?> precioMayor(@RequestParam("precio") double prodprecio){
        if (prodprecio <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("El parámetro 'precio' debe ser mayor a cero.");
        }
        List<Productos> productos = productosservice.precioMayor(prodprecio);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/precioMayorquery")
    public ResponseEntity<?> precioMayorquery(@RequestParam("precio") double prodprecio){
        if (prodprecio <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("El parámetro 'precio' debe ser mayor a cero.");
        }
        List<Productos> productos = productosservice.precioMayorquery(prodprecio);
        return ResponseEntity.ok(productos);
    }
    @GetMapping("/productosdto")
    public ResponseEntity<?> readAllDto(){
        List<?> productosDto = productosservice.readAll();
        if (productosDto == null || productosDto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No hay productos disponibles para mostrar con DTO.");
        }
        return ResponseEntity.ok(productosDto);
    }
}

package com.example.tienda17.feign;

import com.example.tienda17.model.ProductosMockapi;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value= "productos", url = "https://68d1b70ce6c0cbeb39a5a961.mockapi.io/api/viajes")
public interface IProductosMockapi {
    @GetMapping("/productos")
    List<ProductosMockapi> ReadAll();
    @GetMapping("/productos/{id}")
    ProductosMockapi ReadById(@PathVariable("id") Integer id);
    @PostMapping("/productos")
    ProductosMockapi create(@RequestBody ProductosMockapi productosMockapi);
    @PutMapping("/productos/{id}")
    ProductosMockapi update(@PathVariable("id") int id, @RequestBody ProductosMockapi productosMockapi);

    @DeleteMapping("/productos/{id}")
    String deleteLogico(@PathVariable("id") Integer id);
}

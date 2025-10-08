package com.example.tienda17.feign;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.tienda17.model.PromocionesProductos;

@FeignClient (value = "PromocionesProductos", url = "https://68d32e16cc7017eec5462d33.mockapi.io/inndata17/tienda")
public interface PromocionesProductosConect {
	 @GetMapping("/PromocionesProductos")
	    List<PromocionesProductos> ReadAll();
	 
	 @GetMapping("/PromocionesProductos/{id}")
	    Optional<PromocionesProductos> ReadAllById(@PathVariable("id") Integer id);
	 
	 @PostMapping("/PromocionesProductos")
	 PromocionesProductos create(@RequestBody PromocionesProductos nuevo);
	 
	 @PutMapping("/PromocionesProductos/{id}")
	 PromocionesProductos update(@PathVariable("id")Integer id, @RequestBody PromocionesProductos actualizado);
	 
	 @DeleteMapping("/PromocionesProductos/{id}")
	 void delete(@PathVariable("id")Integer id);

}

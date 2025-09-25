package inndata172.ejercicioTarea.controller;

import inndata172.ejercicioTarea.entity.Productos;
import inndata172.ejercicioTarea.service.impl.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tienda")
public class ProductosController {
    @Autowired
    ProductosService productosservice;

    @GetMapping("/productos")
    public List<Productos> readAll(){
        return productosservice.ReadAll();
    }
    @GetMapping("/productos/{id}")
    public Optional<Productos> readById(@PathVariable Integer id){
        return productosservice.ReadById(id);}
    @PostMapping("/crearprod")
    public Productos create(@RequestBody Productos productos){
        return productosservice.create(productos);
    }
    @PutMapping("/actualizaprod")
    public Productos update(@RequestBody Productos productos){
        return productosservice.update(productos);
    }
    @DeleteMapping("/productos/{id}")
    public String delete(@PathVariable Integer id){
        return productosservice.deleteById(id);
    }
}

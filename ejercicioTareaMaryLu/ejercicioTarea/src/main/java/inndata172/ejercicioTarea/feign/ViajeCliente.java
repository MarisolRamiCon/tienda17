package inndata172.ejercicioTarea.feign;

import inndata172.ejercicioTarea.model.Viajes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Enlace con monckapi / nombre de la tabla y la direccion url
@FeignClient(value = "viajescdmx", url = "https://68d1b70ce6c0cbeb39a5a961.mockapi.io/api/viajes" )
public interface ViajeCliente {

    /*Mostrar todo el contenido de la base Viajescdmx*/
@GetMapping("/viajescdmx")
    List<Viajes> ReadAll();

    /*Mostrar contenido por ID*/
@GetMapping("/viajescdmx/{id}")
    Viajes ReadById(@PathVariable("id") Integer id);

    /*Crear Registro*/
@PostMapping("/viajescdmx")
    Viajes create(@RequestBody Viajes viajes);

    /*Actualizar registro por ID*/
@PutMapping("viajescdmx/{id}")
    Viajes update(@PathVariable("id") int id, @RequestBody Viajes viajes);

    /*Eliminar registro por ID*/
    @DeleteMapping("viajescdmx/{id}")
    void delete(@PathVariable("id") int id);
}

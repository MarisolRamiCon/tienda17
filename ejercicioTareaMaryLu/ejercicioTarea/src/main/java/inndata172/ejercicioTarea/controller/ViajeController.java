package inndata172.ejercicioTarea.controller;

import inndata172.ejercicioTarea.model.Viajes;
import inndata172.ejercicioTarea.service.impl.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viajes")

public class ViajeController {
    @Autowired
    ViajeService viajeService;
    @GetMapping("/viajescdmx")
    public List<Viajes> ReadAll(){
        return viajeService.ReadAll();
    }
    @GetMapping("/viajescdmx/{id}")
    public Viajes readById(@PathVariable Integer id){
        return viajeService.ReadById(id);
    }
    @PostMapping("/viajescdmx")
    public String create(@RequestBody Viajes viajes){
        String c = "Se creo el registro nuevo: ";
        return c + viajeService.create(viajes);
    }
    @PutMapping("/viajescdmx/{id}")
    public String update(@RequestBody Viajes viajes, @PathVariable Integer id){
        viajeService.update(id, viajes);
        String a = "Se actualiza el registro: ";
        return a + id;
    }
    @DeleteMapping("/viajescdmx/{id}")
    public String delete(@PathVariable Integer id){
        viajeService.delete(id);
        String d = "Se elimina el registro: ";
        return d + id;
    }
}

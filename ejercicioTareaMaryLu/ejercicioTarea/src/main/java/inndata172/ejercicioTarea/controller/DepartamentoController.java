package inndata172.ejercicioTarea.controller;

import inndata172.ejercicioTarea.entity.Departamento;
import inndata172.ejercicioTarea.service.impl.DepartamentoService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class DepartamentoController {
    @Autowired
    DepartamentoService departamentoService;
    /*
    * Metodos CRUD
    * C- CREATE R-READ  U- UPDATE D-DELETE
    * Para read la anotation es @GetMapping*/
    @GetMapping("/departamentos")
    public List<Departamento> readAll(){
        return departamentoService.ReadAll();
    }
    @GetMapping("/departamentos/{id}")
    public Optional<Departamento> readById(@PathVariable Integer id){
        return departamentoService.ReadById(id);
    }
    /*Para diferenciar el metodo create es post y el metodo update es put*/
    @PostMapping("/departamentos")
    public Departamento create(@RequestBody Departamento departamento){
        return departamentoService.create(departamento);
    }
    //Para el metodo actualizar
    @PutMapping("/departamentos")
    public Departamento update(@RequestBody Departamento departamento){
        return departamentoService.update(departamento);
    }
    @DeleteMapping("/departamentos/{id}")
    public String delete(@PathVariable Integer id){
        return departamentoService.deleteById(id);
    }
    @GetMapping("/precioMayor")
    public List<Departamento> precioMayor(@PathParam("precio") Double precio){
        return departamentoService.precioMayor(precio);
    }

    @GetMapping("/m2Precio")
    public List<Departamento> m2Precio(@PathParam("m2") Integer m2,@PathParam("precio")Double precio){
        return departamentoService.precioM2(String.valueOf(m2),precio);
    }

}

package inndata172.ejercicioTarea.controller;

import inndata172.ejercicioTarea.entity.Persona;
import inndata172.ejercicioTarea.model.PersonaDto;
import inndata172.ejercicioTarea.service.IPersonaService;
import inndata172.ejercicioTarea.service.impl.PersonaService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tarea")
public class PersonaController {
    @Autowired
    PersonaService personaservice;
   /* @GetMapping("/personas")
    public List<Persona> readAll(){
        return personaservice.ReadAll();
    }*/

    @GetMapping("/personasdto")
    public List<PersonaDto> readAll(){
        return personaservice.readAll();
    }
    @GetMapping("/personas/{id}")
    public Optional<Persona> readById(@PathVariable Integer id){
        return personaservice.ReadById(id);
    }
    @PostMapping("/crear")
    public Persona create(@RequestBody Persona persona){
        return personaservice.create(persona);
    }
    @PutMapping("/actualizar")
    public Persona update(@RequestBody Persona persona){
        return personaservice.update(persona);
    }
    @GetMapping("/edadMayor")
    public List<Persona> edadMayor(@PathParam("edad") int edad){
        return personaservice.edadMayor(edad);
    }
    @GetMapping("/edadMayorquery")
    public List<Persona> edadMAyorquery(@PathParam("edad") Integer edad){
        return personaservice.edadMayorquery(edad);
    }
}

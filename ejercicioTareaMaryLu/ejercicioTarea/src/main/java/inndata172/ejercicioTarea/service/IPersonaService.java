package inndata172.ejercicioTarea.service;

import inndata172.ejercicioTarea.entity.Persona;
import inndata172.ejercicioTarea.model.PersonaDto;

import java.util.List;
import java.util.Optional;

public interface IPersonaService {
    //public List<Persona> ReadAll();
    public Optional<Persona> ReadById(Integer id_persona);
    public Persona create(Persona persona);
    public Persona update(Persona persona);
    public String deleteById(Integer id_persona);
    public List<Persona> edadMayor(int edad);
    public List<Persona> edadMayorquery(Integer edad);
    public List<PersonaDto> readAll();
}

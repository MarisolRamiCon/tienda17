package inndata172.ejercicioTarea.service.impl;

import inndata172.ejercicioTarea.entity.Persona;
import inndata172.ejercicioTarea.model.PersonaDto;
import inndata172.ejercicioTarea.repository.IPersonaRepository;
import inndata172.ejercicioTarea.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaService implements IPersonaService {
    @Autowired
    IPersonaRepository personarepository;

    /*@Override
    public List<Persona> ReadAll(){
        return personarepository.findByActivoTrue();
    }*/

    @Override
    public Optional<Persona> ReadById(Integer id) {
        return personarepository.findById(id);
    }

    @Override
    public Persona create(Persona persona) {
        return personarepository.save(persona);}

    @Override
    public Persona update(Persona persona) {
        return personarepository.save(persona);
    }

    @Override
    public String deleteById(Integer id_persona) {
        return "";
    }

    @Override
    public List<Persona> edadMayor(int edad) {
        return personarepository.findByEdadGreaterThan(edad);
    }

    @Override
    public List<Persona> edadMayorquery(Integer edad) {
            return personarepository.edadMayorquery(edad);
    }

   @Override
    public List<PersonaDto> readAll() {
        return personarepository.findAll().stream().map(
                persona -> {
                    PersonaDto personaDto= new PersonaDto(
                            persona.getPnombre(), persona.getEdad(),persona.getDepartamento().getIddepartamento()
                    );
                    return personaDto;
                }
        ).toList();
    }
}

package inndata172.ejercicioTarea.service.impl;

import inndata172.ejercicioTarea.entity.Persona;
import inndata172.ejercicioTarea.entity.Departamento;
import inndata172.ejercicioTarea.model.PersonaDto;
import inndata172.ejercicioTarea.repository.IPersonaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PersonaServiceTest {

    @Mock
    private IPersonaRepository personaRepository;

    @InjectMocks
    private PersonaService personaService;

    private Persona persona;
    private Departamento departamento;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        departamento = new Departamento();
        departamento.setIddepartamento(10);
        departamento.setDnombre("Ventas");
        persona = new Persona();
        persona.setIdpersona(1);
        persona.setPnombre("Daniel");
        persona.setEdad(25);
        persona.setDepartamento(departamento);
    }

    @Test
    void testReadById() {
        when(personaRepository.findById(1)).thenReturn(Optional.of(persona));
        Optional<Persona> result = personaService.ReadById(1);
        assertTrue(result.isPresent());
        assertEquals("Daniel", result.get().getPnombre());
        verify(personaRepository, times(1)).findById(1);
    }

    @Test
    void testCreate() {
        when(personaRepository.save(persona)).thenReturn(persona);
        Persona result = personaService.create(persona);
        assertNotNull(result);
        assertEquals("Daniel", result.getPnombre());
        verify(personaRepository, times(1)).save(persona);
    }

    @Test
    void testUpdate() {
        persona.setEdad(30);
        when(personaRepository.save(persona)).thenReturn(persona);
        Persona result = personaService.update(persona);
        assertEquals(30, result.getEdad());
        verify(personaRepository, times(1)).save(persona);
    }

    @Test
    void testDeleteById() {
        String result = personaService.deleteById(1);
        assertEquals("", result);
    }

    @Test
    void testEdadMayor() {
        when(personaRepository.findByEdadGreaterThan(20)).thenReturn(Arrays.asList(persona));
        List<Persona> result = personaService.edadMayor(20);
        assertEquals(1, result.size());
        assertEquals("Daniel", result.get(0).getPnombre());
        verify(personaRepository, times(1)).findByEdadGreaterThan(20);
    }

    @Test
    void testEdadMayorQuery() {
        when(personaRepository.edadMayorquery(18)).thenReturn(Arrays.asList(persona));
        List<Persona> result = personaService.edadMayorquery(18);
        assertEquals(1, result.size());
        assertEquals(25, result.get(0).getEdad());
        verify(personaRepository, times(1)).edadMayorquery(18);
    }

    @Test
    void testReadAll() {
        when(personaRepository.findAll()).thenReturn(Arrays.asList(persona));
        List<PersonaDto> result = personaService.readAll();
        assertEquals(1, result.size());
        assertEquals("Daniel", result.get(0).getPnombre());
        assertEquals(25, result.get(0).getEdad());
        assertEquals(10, result.get(0).getIddepartamento());
        verify(personaRepository, times(1)).findAll();
    }
}
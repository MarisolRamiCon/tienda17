package inndata172.ejercicioTarea.service.impl;

import inndata172.ejercicioTarea.feign.ViajeCliente;
import inndata172.ejercicioTarea.model.Viajes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ViajeServiceTest {

    @Mock
    private ViajeCliente viajeCliente;

    @InjectMocks
    private ViajeService viajeService;

    private Viajes viaje;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        viaje = new Viajes(1, "CDMX", 10);
    }

    @Test
    void testReadAll() {
        when(viajeCliente.ReadAll()).thenReturn(Arrays.asList(viaje));
        List<Viajes> result = viajeService.ReadAll();
        assertEquals(1, result.size());
        assertEquals("CDMX", result.get(0).getNombre());
        verify(viajeCliente, times(1)).ReadAll();
    }

    @Test
    void testReadById() {
        when(viajeCliente.ReadById(1)).thenReturn(viaje);
        Viajes result = viajeService.ReadById(1);
        assertNotNull(result);
        assertEquals("CDMX", result.getNombre());
        verify(viajeCliente, times(1)).ReadById(1);
    }

    @Test
    void testCreate() {
        when(viajeCliente.create(viaje)).thenReturn(viaje);
        Viajes result = viajeService.create(viaje);
        assertEquals(10, result.getBoleto());
        verify(viajeCliente, times(1)).create(viaje);
    }

    @Test
    void testUpdate() {
        viaje.setBoleto(20);
        when(viajeCliente.update(1, viaje)).thenReturn(viaje);
        Viajes result = viajeService.update(1, viaje);
        assertEquals(20, result.getBoleto());
        verify(viajeCliente, times(1)).update(1, viaje);
    }

    @Test
    void testDelete() {
        doNothing().when(viajeCliente).delete(1);
        viajeService.delete(1);
        verify(viajeCliente, times(1)).delete(1);
    }
}

package com.example.tienda17.service.impl;


import com.example.tienda17.feign.IAspirante;
import com.example.tienda17.model.Aspirantes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AspiranteServiceTest {

    @Mock
    private IAspirante iAspirante;

    @InjectMocks
    private AspirantesServices aspiranteService;

    private Aspirantes activo;
    private Aspirantes inactivo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        activo = new Aspirantes();
        activo.setId(Integer.valueOf("1"));
        activo.setActivo(true);

        inactivo = new Aspirantes();
        inactivo.setId(Integer.valueOf("2"));
        inactivo.setActivo(false);
    }

    @Test
    void testReadAll_filtraSoloActivos() {
        when(iAspirante.readAll()).thenReturn(List.of(activo, inactivo));

        List<Aspirantes> result = aspiranteService.readAll();

        assertEquals(1, result.size());
        assertTrue(result.get(0).getActivo());
        verify(iAspirante, times(1)).readAll();
    }

    @Test
    void testReadById_devuelveAspirante() {
        when(iAspirante.readById("1")).thenReturn(activo); // o usa 1 si readById espera Integer

        Aspirantes result = aspiranteService.readById("1");

        assertNotNull(result);
        assertEquals(1, result.getId()); // ✅ tipo consistente
    }
    @Test
    void testCreate_guardaAspirante() {
        when(iAspirante.create(activo)).thenReturn(activo);

        Aspirantes result = aspiranteService.create(activo);

        assertEquals(activo, result);
        verify(iAspirante, times(1)).create(activo);
    }

    @Test
    void testUpdate_modificaAspirante() {
        when(iAspirante.update("1", activo)).thenReturn(activo);

        Aspirantes result = aspiranteService.update("1", activo);

        assertEquals(activo, result);
        verify(iAspirante, times(1)).update("1", activo);
    }

    @Test
    void testDelete_cambiaEstadoAInactivo() {
        when(iAspirante.readById("1")).thenReturn(activo);
        when(iAspirante.update(eq("1"), any(Aspirantes.class))).thenReturn(activo);

        Aspirantes result = aspiranteService.delete("1");

        assertFalse(result.getActivo(), "El aspirante debe quedar inactivo");
        verify(iAspirante, times(1)).update(eq("1"), any(Aspirantes.class));
    }

    @Test
    void testDelete_lanzaExcepcionSiNoExiste() {
        when(iAspirante.readById("99")).thenReturn(null);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> aspiranteService.delete("99")
        );

        assertEquals("Aspirante no encontrado con id: 99", ex.getMessage());
        verify(iAspirante, times(1)).readById("99");
        verify(iAspirante, never()).update(anyString(), any());
    }
}

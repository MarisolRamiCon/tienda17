package inndata172.ejercicioTarea.service.impl;

import inndata172.ejercicioTarea.entity.Productos;
import inndata172.ejercicioTarea.repository.IProductosRepository;
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

class ProductosServiceTest {

    @Mock
    private IProductosRepository productosRepository;

    @InjectMocks
    private ProductosService productosService;

    private Productos producto;

    @BeforeEach
    void setProductos() {
        MockitoAnnotations.openMocks(this);
        producto = new Productos();
        producto.setIdproducto(1);
        producto.setProdnombre("Laptop");
        producto.setProdprecio(1200.0);
    }

    @Test
    void testReadAll() {
        when(productosRepository.findAll()).thenReturn(Arrays.asList(producto));
        List<Productos> lista = productosService.ReadAll();
        assertNotNull(lista);
        assertEquals(1, lista.size());
        assertEquals("Laptop", lista.get(0).getProdnombre());
        assertEquals(1200.0, lista.get(0).getProdprecio());
        verify(productosRepository, times(1)).findAll();
    }

    @Test
    void testReadById() {
        when(productosRepository.findById(1)).thenReturn(Optional.of(producto));
        Optional<Productos> encontrado = productosService.ReadById(1);
        assertTrue(encontrado.isPresent());
        assertEquals("Laptop", encontrado.get().getProdnombre());
        verify(productosRepository, times(1)).findById(1);
    }

    @Test
    void testCreate() {
        when(productosRepository.save(producto)).thenReturn(producto);
        Productos creado = productosService.create(producto);
        assertNotNull(creado);
        assertEquals("Laptop", creado.getProdnombre());
        assertEquals(1200.0, creado.getProdprecio());
        verify(productosRepository, times(1)).save(producto);
    }

    @Test
    void testUpdate() {
        producto.setProdnombre("PC Gamers");
        producto.setProdprecio(1500.0);
        when(productosRepository.save(producto)).thenReturn(producto);
        Productos actualizado = productosService.update(producto);
        assertEquals("PC Gamers", actualizado.getProdnombre());
        assertEquals(1500.0, actualizado.getProdprecio());
        verify(productosRepository, times(1)).save(producto);
    }

    @Test
    void testDeleteById() {
        doNothing().when(productosRepository).deleteById(1);
        String resultado = productosService.deleteById(1);
        assertEquals("Registro eliminado: 1", resultado);
        verify(productosRepository, times(1)).deleteById(1);
    }
}

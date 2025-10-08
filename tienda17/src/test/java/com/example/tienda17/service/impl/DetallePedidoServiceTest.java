package com.example.tienda17.service.impl;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import com.example.tienda17.entity.DetallePedido;
import com.example.tienda17.exceptions.ResourceNotFoundException;
import com.example.tienda17.model.DetalleRequest;
import com.example.tienda17.repository.DetallePedidoRepository;

@ExtendWith(MockitoExtension.class)
public class DetallePedidoServiceTest {
	@Mock
	private DetallePedidoRepository detallePedidoRepository;

	@InjectMocks
	private DetallePedidoService detallePedidoService;

	private DetallePedido detalleActivo;
	private DetallePedido detalleInactivo;

	@BeforeEach
	void setUp() {
		detalleActivo = new DetallePedido();
		detalleActivo.setId(1);
		detalleActivo.setPedido(10);
		detalleActivo.setProducto(20);
		detalleActivo.setCantidad(5);
		detalleActivo.setPrecioUnitario(100.0);
		detalleActivo.setActivo(true);

		detalleInactivo = new DetallePedido();
		detalleInactivo.setId(2);
		detalleInactivo.setActivo(false);
	}

	// -------------------------------------------------------
	// TESTS DE LECTURA
	// -------------------------------------------------------

	@Test
	void testReadAll_FiltraSoloActivos() {
		when(detallePedidoRepository.findAll()).thenReturn(List.of(detalleActivo, detalleInactivo));

		List<DetallePedido> result = detallePedidoService.readAll();

		assertEquals(1, result.size());
		assertTrue(result.get(0).getActivo());
	}

	@Test
	void testReadAll_LanzaServiceException() {
		when(detallePedidoRepository.findAll()).thenThrow(new RuntimeException("DB Error"));
		assertThrows(ServiceException.class, () -> detallePedidoService.readAll());
	}

	@Test
	void testReadById_Existe() {
		when(detallePedidoRepository.findById(1)).thenReturn(Optional.of(detalleActivo));

		Optional<DetallePedido> result = detallePedidoService.ReadById(1);

		assertTrue(result.isPresent());
		assertEquals(10, result.get().getPedido());
	}

	@Test
	void testReadById_LanzaServiceException() {
		when(detallePedidoRepository.findById(anyInt())).thenThrow(new RuntimeException("DB Error"));
		assertThrows(ServiceException.class, () -> detallePedidoService.ReadById(1));
	}

	// -------------------------------------------------------
	// TESTS DE CREACIÓN Y ACTUALIZACIÓN
	// -------------------------------------------------------

	@Test
	void testCreate_OK() {
		when(detallePedidoRepository.save(detalleActivo)).thenReturn(detalleActivo);

		DetallePedido result = detallePedidoService.create(detalleActivo);

		assertNotNull(result);
		verify(detallePedidoRepository).save(detalleActivo);
	}

	@Test
	void testCreate_DataIntegrityViolation_LanzaServiceException() {
		when(detallePedidoRepository.save(any())).thenThrow(new DataIntegrityViolationException("Error"));

		assertThrows(ServiceException.class, () -> detallePedidoService.create(detalleActivo));
	}

	@Test
	void testUpdate_OK() {
		when(detallePedidoRepository.save(detalleActivo)).thenReturn(detalleActivo);

		DetallePedido result = detallePedidoService.update(detalleActivo);

		assertEquals(detalleActivo, result);
		verify(detallePedidoRepository).save(detalleActivo);
	}

	@Test
	void testUpdate_LanzaServiceException() {
		when(detallePedidoRepository.save(any())).thenThrow(new RuntimeException("Error"));
		assertThrows(ServiceException.class, () -> detallePedidoService.update(detalleActivo));
	}

	// -------------------------------------------------------
	// TESTS DELETE LÓGICO
	// -------------------------------------------------------

	@Test
	void testDeleteById_Existe() {
		when(detallePedidoRepository.findById(1)).thenReturn(Optional.of(detalleActivo));

		String result = detallePedidoService.deleteById(1);

		assertEquals("Detalle pedido desactivado correctamente", result);
		verify(detallePedidoRepository).save(any());
	}

	@Test
	void testDeleteById_NoExiste_LanzaResourceNotFound() {
		when(detallePedidoRepository.findById(99)).thenReturn(Optional.empty());
		assertThrows(ResourceNotFoundException.class, () -> detallePedidoService.deleteById(99));
	}

	@Test
	void testDeleteById_LanzaServiceException() {
		when(detallePedidoRepository.findById(anyInt())).thenThrow(new RuntimeException("Error"));
		assertThrows(ServiceException.class, () -> detallePedidoService.deleteById(1));
	}

	// -------------------------------------------------------
	// TESTS DE MÉTODOS PERSONALIZADOS
	// -------------------------------------------------------

	@Test
	void testFindByPrecioUnitarioGreaterThan() {
		when(detallePedidoRepository.findByPrecioUnitarioGreaterThan(50.0)).thenReturn(List.of(detalleActivo));

		List<DetallePedido> result = detallePedidoService.findByPrecioUnitarioGreaterThan(50.0);

		assertEquals(1, result.size());
		verify(detallePedidoRepository).findByPrecioUnitarioGreaterThan(50.0);
	}

	@Test
	void testFindByPrecioUnitarioGreaterThanEqual() {
		when(detallePedidoRepository.findByPrecioUnitarioGreaterThanEqual(100.0)).thenReturn(List.of(detalleActivo));

		List<DetallePedido> result = detallePedidoService.findByPrecioUnitarioGreaterThanEqual(100.0);

		assertEquals(1, result.size());
		verify(detallePedidoRepository).findByPrecioUnitarioGreaterThanEqual(100.0);
	}

	@Test
	void testFindByPrecioUnitario_QueryPersonalizada() {
		when(detallePedidoRepository.findByPrecioUnitario(80.0)).thenReturn(List.of(detalleActivo));

		List<DetallePedido> result = detallePedidoService.findByPrecioUnitario(80.0);

		assertEquals(1, result.size());
		verify(detallePedidoRepository).findByPrecioUnitario(80.0);
	}

	// -------------------------------------------------------
	// TESTS DE MÉTODOS CON DetalleRequest
	// -------------------------------------------------------

	@Test
	void testReadAllDevuelveDetalleRequest() {
		when(detallePedidoRepository.findAll()).thenReturn(List.of(detalleActivo));

		List<DetalleRequest> result = detallePedidoService.ReadAll();

		assertEquals(1, result.size());
		assertEquals(detalleActivo.getPedido(), result.get(0).getPedido());
	}

	@Test
	void testReadByIdDevuelveDetalleRequest() {
		when(detallePedidoRepository.findById(1)).thenReturn(Optional.of(detalleActivo));

		Optional<DetalleRequest> result = detallePedidoService.readById(1);

		assertTrue(result.isPresent());
		assertEquals(detalleActivo.getProducto(), result.get().getProducto());
	}

}

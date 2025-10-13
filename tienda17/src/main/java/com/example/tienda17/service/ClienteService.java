package com.ejemploTienda.Service;


import java.io.Console;



import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.support.RepositoryComposition;
import org.springframework.stereotype.Service;

import com.ejemploTienda.Dto.ClienteDto;
import com.ejemploTienda.Dto.ClienteRequest;
import com.ejemploTienda.Dto.ClienteResponse;
import com.ejemploTienda.Dto.DatosDto;
import com.ejemploTienda.Entity.ClienteEntity;
import com.ejemploTienda.Repository.RepositoryCliente;

@Service
public class ClienteService implements IClienteService {

    private static final Logger logger = Logger.getLogger(ClienteService.class.getName());

    @Autowired
    private RepositoryCliente repositoryCliente;
    
    //query implementado para correo

    public ClienteService(RepositoryCliente repositoryCliente) {
        this.repositoryCliente = repositoryCliente;
    }

    public List<DatosDto> obtenerClientesActivosNombreYCorreo() {
        return repositoryCliente.listarClientesActivosNombreYCorreo();
    }

    
//busca los clientes activos 
    //ya se implemento el responseDto
    @Override
    public List<ClienteResponse> ReadAll() {
        try {
            return repositoryCliente.findByEstadoTrue()
                    .stream()
                    .map(this::mapToResponse)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.severe("Error al listar clientes: " + e.getMessage());
            throw new RuntimeException("No se pudieron listar los clientes.");
        }
    }
//busca por nombre
    public ClienteEntity getByNombre(String nombre) {
        try {
            return repositoryCliente.findByNombre(nombre)
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado: " + nombre));
        } catch (RuntimeException e) {
            logger.warning("Cliente no encontrado por nombre: " + nombre);
            throw e;
        } catch (Exception e) {
            logger.severe("Error inesperado al buscar cliente por nombre: " + e.getMessage());
            throw new RuntimeException("Error al buscar el cliente por nombre.");
        }
    }
//inserta nuevos clientes 
    public ClienteResponse create(ClienteRequest request) {
        try {
        
            ClienteEntity cliente = new ClienteEntity();
        	cliente.setID_cliente(request.getId());
            cliente.setNombre(request.getNombre());
            cliente.setApellido(request.getApellido());
            cliente.setDireccion(request.getDireccion());
            cliente.setCorreo(request.getCorreo());
            cliente.setTelefono(request.getTelefono());

            ClienteEntity saved = repositoryCliente.save(cliente);
            return mapToResponse(saved);
        } catch (Exception e) {
        	 e.printStackTrace();
            logger.severe("Error al crear cliente: " + e.getMessage());
            throw new RuntimeException("No se pudo crear el cliente.",e);
        }
    }

	//busca por ID
	public ClienteDto getById(Long id) {
        try {
            ClienteEntity cliente = repositoryCliente.findById(id)
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));

            return new ClienteDto(
                    cliente.getID_cliente(),
                    cliente.getNombre(),
                    cliente.getApellido(),
                    cliente.getDireccion(),
                    cliente.getCorreo(),
                    cliente.getTelefono()
            );
        } catch (RuntimeException e) {
           
            throw new RuntimeException("Error al obtener el cliente: " + e.getMessage());
        } catch (Exception e) {
            
            throw new RuntimeException("Ocurrió un error inesperado al obtener el cliente: " + e.getMessage());
        }
    }

    // Nuevo método para borrado lógico
	public void deleteLogico(Long id) {
        try {
            ClienteEntity cliente = repositoryCliente.findById(id)
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
            cliente.setEstado(false);
            repositoryCliente.save(cliente);
        } catch (RuntimeException e) {
            logger.warning("Error al eliminar cliente por ID: " + id);
            throw e;
        } catch (Exception e) {
            logger.severe("Error inesperado al eliminar cliente: " + e.getMessage());
            throw new RuntimeException("Error al eliminar el cliente.");
        }
    }
	
	private ClienteResponse mapToResponse(ClienteEntity cliente) {
        ClienteResponse response = new ClienteResponse();
        response.setId(cliente.getID_cliente());
        response.setNombre(cliente.getNombre());
        response.setApellido(cliente.getApellido());
        response.setDireccion(cliente.getDireccion());
        response.setCorreo(cliente.getCorreo());
        response.setTelefono(cliente.getTelefono());
        return response;
    }
	
}



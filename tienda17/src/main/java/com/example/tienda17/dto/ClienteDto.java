package com.example.tienda17.dto;
import jakarta.persistence.Column;

public class ClienteDto {
    private Long ID_cliente;

    private String nombre;

    private String apellido;

    private String direccion;

    private String correo;

    private String telefono;



    public ClienteDto(Long iD_cliente, String nombre, String apellido, String direccion, String correo,
                      String telefono) {

        this.ID_cliente = iD_cliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
    }

    public ClienteDto() {
        super();

    }

    public Long getID_cliente() {
        return ID_cliente;
    }

    public void setID_cliente(Long iD_cliente) {
        ID_cliente = iD_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }



}

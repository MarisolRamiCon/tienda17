package com.ejemploTienda.Entity;


import jakarta.persistence.Column;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@SuppressWarnings("unused")
@Entity
@Table(name = "Cliente")  // Debe coincidir con la tabla Oracle

public class ClienteEntity {
	@Id
	@Column(name="ID_cliente")
	 private Long ID_cliente;
	@Column(name="nombre")
	    private String nombre;
	@Column(name="apellido")
	    private String apellido;
	@Column(name="direccion")
	    private String direccion;
	@Column(name="correo")
	    private String correo;
	@Column(name="telefono")
	    private String telefono;
	    @Column(name="estado")
	 private Boolean estado ;
		public ClienteEntity(Long iD_cliente, String nombre, String apellido, String direccion, String correo,
				String telefono, Boolean estado) {
			super();
			ID_cliente = iD_cliente;
			this.nombre = nombre;
			this.apellido = apellido;
			this.direccion = direccion;
			this.correo = correo;
			this.telefono = telefono;
			this.estado = estado;
		}
		
		public ClienteEntity() {
			super();
			// TODO Auto-generated constructor stub
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
		public Boolean getEstado() {
			return estado;
		}
		public void setEstado(Boolean estado) {
			this.estado = estado;
		} 	
}


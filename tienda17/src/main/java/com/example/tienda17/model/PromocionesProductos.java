package com.example.tienda17.model;

public class PromocionesProductos {
private Integer id;
private String nombre;
private Double precio;
private Integer stock;
private boolean activo = true;
public PromocionesProductos() {
	
}
public PromocionesProductos(Integer id, String nombre, Double precio, Integer stock, boolean activo) {
	super();
	this.id = id;
	this.nombre = nombre;
	this.precio = precio;
	this.stock = stock;
	this.activo = activo;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public Double getPrecio() {
	return precio;
}
public void setPrecio(Double precio) {
	this.precio = precio;
}
public Integer getStock() {
	return stock;
}
public void setStock(Integer stock) {
	this.stock = stock;
}
public boolean isActivo() {
	return activo;
}
public void setActivo(boolean activo) {
	this.activo = activo;
}


}
package com.example.tienda17.model;

public class DetalleRequest {
	private int pedido;
	private int producto;
	private int cantidad;
	public DetalleRequest(int pedido, int producto, int cantidad) {
		super();
		this.pedido = pedido;
		this.producto = producto;
		this.cantidad = cantidad;
	}
	public DetalleRequest() {
	}
	public int getPedido() {
		return pedido;
	}
	public void setPedido(int pedido) {
		this.pedido = pedido;
	}
	public int getProducto() {
		return producto;
	}
	public void setProducto(int producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}

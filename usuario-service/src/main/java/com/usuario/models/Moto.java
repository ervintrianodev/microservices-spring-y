package com.usuario.models;

public class Moto {

	private Long id;
	private String marca;
	private String modelo;
	private Long usuarioId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	@Override
	public String toString() {
		return "Moto [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", usuarioId=" + usuarioId + "]";
	}

}

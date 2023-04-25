package com.usuario.services;

import java.util.List;

import com.usuario.models.Carro;

public interface ICarroService {
	public List<Carro> findAll();

	public List<Carro> findByUsuario(Long usuarioId);

	public Carro findById(Long id);

	public Carro save(Carro carro);

}
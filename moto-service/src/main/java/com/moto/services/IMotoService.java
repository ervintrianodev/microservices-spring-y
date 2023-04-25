package com.moto.services;

import java.util.List;

import com.moto.models.Moto;

public interface IMotoService {
	public List<Moto> findAll();

	public Moto findById(Long id);

	public List<Moto> findMotoByUsuario(Long usuarioId);

	public Moto save(Moto moto);
}

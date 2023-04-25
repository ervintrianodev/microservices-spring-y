package com.moto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moto.models.Moto;
import com.moto.repositories.IMotosRepositoty;
import com.moto.services.IMotoService;

@Service
public class MotoServiceImpl implements IMotoService {

	@Autowired
	private IMotosRepositoty iMotosRepositoty;

	@Override
	public List<Moto> findAll() {
		return iMotosRepositoty.findAll();
	}

	@Override
	public Moto findById(Long id) {
		return iMotosRepositoty.findById(id).orElse(null);
	}

	@Override
	public List<Moto> findMotoByUsuario(Long usuarioId) {
		return iMotosRepositoty.findByUsuarioId(usuarioId);
	}

	@Override
	public Moto save(Moto moto) {
		return iMotosRepositoty.save(moto);
	}

}

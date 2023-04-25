package com.usuario.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usuario.models.Carro;
import com.usuario.repository.ICarroRepository;
import com.usuario.services.ICarroService;

@Service
public class CarroServiceImpl implements ICarroService {

	@Autowired
	private ICarroRepository iCarroRepository;

	@Override
	public List<Carro> findAll() {
		return iCarroRepository.findAll();
	}

	@Override
	public List<Carro> findByUsuario(Long usuarioId) {
		return iCarroRepository.findByUsuarioId(usuarioId);
	}

	@Override
	public Carro findById(Long id) {
		return iCarroRepository.findById(id).orElse(null);
	}

	@Override
	public Carro save(Carro carro) {
		return iCarroRepository.save(carro);
	}

}
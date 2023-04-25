package com.usuario.services;

import java.util.List;
import java.util.Map;

import com.usuario.entities.Usuario;
import com.usuario.models.Carro;
import com.usuario.models.Moto;

public interface IUsuarioService {

	public List<Usuario> findAll();

	public Usuario findById(Long id);

	public Usuario save(Usuario usuario);

	public void delete(Long id);

	public List<Carro> findAllCars(Long usuarioId);

	public List<Moto> findAllMotos(Long usuarioId);
	
	public Map<String, Object> findAllObject(Long usuarioId);

	public Carro saveCarro(Carro carro, Long usuarioId);

	public Moto saveMoto(Moto moto, Long usuarioId);

}

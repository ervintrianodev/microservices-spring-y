package com.usuario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usuario.models.Carro;

@Repository
public interface ICarroRepository extends JpaRepository<Carro, Long> {
	List<Carro> findByUsuarioId(Long usuarioId);
}

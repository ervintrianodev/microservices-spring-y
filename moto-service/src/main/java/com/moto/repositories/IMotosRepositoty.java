package com.moto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moto.models.Moto;

@Repository
public interface IMotosRepositoty extends JpaRepository<Moto, Long> {

	List<Moto> findByUsuarioId(Long userId);

}

package com.usuario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usuario.entities.Usuario;

@Repository
public interface IUsuariosRepository extends JpaRepository<Usuario, Long> {

}

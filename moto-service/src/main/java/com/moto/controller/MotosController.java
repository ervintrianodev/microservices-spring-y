package com.moto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moto.models.Moto;
import com.moto.services.IMotoService;

@RestController
@RequestMapping("/motos")
public class MotosController {

	@Autowired
	private IMotoService iMotoService;

	@GetMapping("/list")
	public ResponseEntity<List<Moto>> findAll() {
		List<Moto> motos = iMotoService.findAll();
		if (motos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(motos);
	}

	@GetMapping("/findbyuser/{userId}")
	public ResponseEntity<List<Moto>> findMotosByUser(@PathVariable("userId") Long id) {
		List<Moto> motos = iMotoService.findMotoByUsuario(id);
		if (motos.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(motos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Moto> findById(@PathVariable("id") Long id) {
		Moto moto = iMotoService.findById(id);
		if (moto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(moto);
	}

	@PostMapping("/save")
	public ResponseEntity<Moto> save(@RequestBody Moto moto) {
		Moto motoNueva = iMotoService.save(moto);
		return ResponseEntity.ok(motoNueva);
	}

}

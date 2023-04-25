package com.usuario.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.models.Carro;
import com.usuario.services.ICarroService;

@RestController
@RequestMapping("/cars")
public class CarroController {

	@Autowired
	private ICarroService iCarroService;

	@GetMapping("/list")
	public ResponseEntity<List<Carro>> findAll() {
		List<Carro> carros = iCarroService.findAll();
		if (carros.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(carros);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Carro> findById(@PathVariable("id") Long id) {
		Carro carro = iCarroService.findById(id);
		if (carro == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(carro);
	}

	@GetMapping("/findbyuser/{userId}")
	public ResponseEntity<List<Carro>> findCarroByUser(@PathVariable("userId") Long id) {
		List<Carro> carros = iCarroService.findByUsuario(id);
		if (carros.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(carros);
	}

	@PostMapping("/save")
	public ResponseEntity<Carro> save(@RequestBody Carro carro) {
		Carro carroDB = iCarroService.save(carro);
		return ResponseEntity.ok(carroDB);
	}
}
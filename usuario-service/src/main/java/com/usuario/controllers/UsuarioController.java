package com.usuario.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.entities.Usuario;
import com.usuario.models.Carro;
import com.usuario.models.Moto;
import com.usuario.services.IUsuarioService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class UsuarioController {

	@Autowired
	private IUsuarioService iUsuarioService;

	@GetMapping("/list")
	public ResponseEntity<List<Usuario>> listUsuarios() {
		List<Usuario> usuarios = iUsuarioService.findAll();
		if (usuarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(usuarios);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUser(@PathVariable("id") Long id) {
		Usuario usuario = iUsuarioService.findById(id);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuario);
	}

	@PostMapping("/save")
	public ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario usuario) {
		Usuario newUser = iUsuarioService.save(usuario);
		return ResponseEntity.ok(newUser);
	}

	@CircuitBreaker(name = "carrosCB", fallbackMethod = "fallBackGetCarros")
	@GetMapping("/carros/{usuarioId}")
	public ResponseEntity<List<Carro>> findCarrosByUser(@PathVariable("usuarioId") Long id) {
		Usuario usuario = iUsuarioService.findById(id);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}
		List<Carro> carros = iUsuarioService.findAllCars(id);
		return ResponseEntity.ok(carros);
	}

	@CircuitBreaker(name = "allCB", fallbackMethod = "fallBackGetAll")
	@GetMapping("/findAll/{userId}")
	public ResponseEntity<Map<String, Object>> findAll(@PathVariable("userId") Long userId) {
		Map<String, Object> findAllObjects = iUsuarioService.findAllObject(userId);
		return ResponseEntity.ok(findAllObjects);
	}

	@CircuitBreaker(name = "motosCB", fallbackMethod = "fallBackGetMotos")
	@GetMapping("/motos/{usuarioId}")
	public ResponseEntity<List<Moto>> findMotosByUser(@PathVariable("usuarioId") Long id) {
		Usuario usuario = iUsuarioService.findById(id);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}
		List<Moto> motos = iUsuarioService.findAllMotos(id);
		return ResponseEntity.ok(motos);
	}

	@CircuitBreaker(name = "carroCB", fallbackMethod = "fallBackSaveCarro")
	@PostMapping("/carro/{usuarioId}")
	public ResponseEntity<Carro> saveCarro(@RequestBody Carro carro, @PathVariable("usuarioId") Long usuarioId) {
		Carro carroNuevo = iUsuarioService.saveCarro(carro, usuarioId);
		return ResponseEntity.ok(carroNuevo);
	}

	@CircuitBreaker(name = "motosCB", fallbackMethod = "FallBackSaveMoto")
	@PostMapping("/motos/{usuarioId}")
	public ResponseEntity<Moto> saveMoto(@RequestBody Moto moto, @PathVariable("usuarioId") Long usuarioId) {
		Moto motoNueva = iUsuarioService.saveMoto(moto, usuarioId);
		return ResponseEntity.ok(motoNueva);
	}

	/****************************************************************************************************
	 * METODOS FALLBACK
	 ****************************************************************************************************/

	private ResponseEntity<String> fallBackGetCarros(@PathVariable("usuarioId") Long usuarioId,
			RuntimeException exception) {
		return new ResponseEntity<String>("El usuario con el id: " + usuarioId + " tiene los carros en el taller",
				HttpStatus.OK);
	}

	private ResponseEntity<String> fallBackSaveCarro(@PathVariable("usuarioId") Long usuarioId,
			@RequestBody Carro carro) {
		return new ResponseEntity<String>(
				"El usuarios con el id: " + usuarioId + " no tiene dinero para comprar un carro", HttpStatus.OK);
	}

	private ResponseEntity<String> fallBackGetMotos(@PathVariable("usuarioId") Long usuarioId,
			RuntimeException exception) {
		return new ResponseEntity<String>("El usuario con el id: " + usuarioId + " tiene las motos en el taller",
				HttpStatus.OK);
	}

	private ResponseEntity<String> fallBackSaveMoto(@PathVariable("usuarioId") Long usuarioId, @RequestBody Carro carro,
			RuntimeException exception) {
		return new ResponseEntity<String>(
				"El usuarios con el id: " + usuarioId + " no tiene dinero para comprar una moto", HttpStatus.OK);
	}

	private ResponseEntity<String> fallBackGetAll(@PathVariable("usuarioId") Long usuarioId,
			RuntimeException exception) {
		return new ResponseEntity<String>(
				"El usuario con el id " + usuarioId + " tiene todos lo vehiculos en el taller", HttpStatus.OK);
	}
}

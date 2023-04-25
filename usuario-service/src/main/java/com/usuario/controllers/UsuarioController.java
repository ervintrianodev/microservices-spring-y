package com.usuario.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/users")
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

	@GetMapping("/carros/{usuarioId}")
	public ResponseEntity<List<Carro>> findCarrosByUser(@PathVariable("usuarioId") Long id) {
		Usuario usuario = iUsuarioService.findById(id);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}
		List<Carro> carros = iUsuarioService.findAllCars(id);
		return ResponseEntity.ok(carros);
	}

	@GetMapping("/findAll/{userId}")
	public ResponseEntity<Map<String, Object>> findAll(@PathVariable("userId") Long userId) {
		Map<String, Object> findAllObjects = iUsuarioService.findAllObject(userId);
		return ResponseEntity.ok(findAllObjects);
	}

	@GetMapping("/motos/{usuarioId}")
	public ResponseEntity<List<Moto>> findMotosByUser(@PathVariable("usuarioId") Long id) {
		Usuario usuario = iUsuarioService.findById(id);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}
		List<Moto> motos = iUsuarioService.findAllMotos(id);
		return ResponseEntity.ok(motos);
	}

	@PostMapping("/carro/{usuarioId}")
	public ResponseEntity<Carro> saveCarro(@RequestBody Carro carro, @PathVariable("usuarioId") Long usuarioId) {
		Carro carroNuevo = iUsuarioService.saveCarro(carro, usuarioId);
		return ResponseEntity.ok(carroNuevo);
	}

	@PostMapping("/motos/{usuarioId}")
	public ResponseEntity<Moto> saveMoto(@RequestBody Moto moto, @PathVariable("usuarioId") Long usuarioId) {
		Moto motoNueva = iUsuarioService.saveMoto(moto, usuarioId);
		return ResponseEntity.ok(motoNueva);
	}

}

package com.usuario.service.servicesImpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.usuario.entities.Usuario;
import com.usuario.feign.clients.ICarroFeignClient;
import com.usuario.feign.clients.IMotoFeignClient;
import com.usuario.models.Carro;
import com.usuario.models.Moto;
import com.usuario.repositories.IUsuariosRepository;
import com.usuario.services.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private IUsuariosRepository iUsuariosRepository;

	@Autowired
	private ICarroFeignClient iCarroFeignClient;

	@Autowired
	private IMotoFeignClient iMotoFeignClient;

	@Override
	public List<Usuario> findAll() {
		return iUsuariosRepository.findAll();
	}

	@Override
	public Usuario findById(Long id) {
		return iUsuariosRepository.findById(id).orElse(null);
	}

	@Override
	public Usuario save(Usuario usuario) {

		return iUsuariosRepository.save(usuario);
	}

	@Override
	public void delete(Long id) {
		iUsuariosRepository.deleteById(id);

	}
//metodos con restTemplate
	public List<Carro> findAllCars(Long usuarioId) {
		ResponseEntity<Carro[]> response = restTemplate
				.getForEntity("http://carros-service/findbyuser/" + usuarioId, Carro[].class);
		Carro[] carrosArray = response.getBody();
		List<Carro> carros = Arrays.asList(carrosArray);
		return carros;
	}

	@Override
	public List<Moto> findAllMotos(Long usuarioId) {
		ResponseEntity<Moto[]> response = restTemplate
				.getForEntity("http://motos-service/findbyuser/" + usuarioId, Moto[].class);
		Moto[] motosArray = response.getBody();
		List<Moto> motos = Arrays.asList(motosArray);
		return motos;
	}
//metodos con feign
	@Override
	public Carro saveCarro(Carro carro, Long usuarioId) {
		carro.setUsuarioId(usuarioId);
		Carro carroNuevo = iCarroFeignClient.save(carro);
		return carroNuevo;
	}

	@Override
	public Moto saveMoto(Moto moto, Long usuarioId) {
		moto.setUsuarioId(usuarioId);
		Moto motoNueva = iMotoFeignClient.save(moto);
		return motoNueva;
	}

	@Override
	public Map<String, Object> findAllObject(Long usuarioId) {
		Map<String, Object> resultados = new HashMap<>();
		Usuario usuario = iUsuariosRepository.findById(usuarioId).orElse(null);
		if (usuario == null) {
			resultados.put("mensaje", "El usuario no existe");
			return resultados;
		}
		resultados.put("usuario", usuario);
		List<Carro> carros = iCarroFeignClient.findCarrosByUser(usuarioId);
		if (carros.isEmpty()) {
			resultados.put("carros", "No hay carros");
		} else {
			resultados.put("carros", carros);
		}

		List<Moto> motos = iMotoFeignClient.findMotosByUser(usuarioId);
		if (motos.isEmpty()) {
			resultados.put("motos", "No hay motos");
		} else {
			resultados.put("motos", motos);
		}
		return resultados;
	}

}

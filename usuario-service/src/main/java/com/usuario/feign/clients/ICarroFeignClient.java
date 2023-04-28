package com.usuario.feign.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.usuario.models.Carro;

@FeignClient(name = "carros-service", url = "http://localhost:8080/api")
public interface ICarroFeignClient {

	@PostMapping("/cars/save")
	public Carro save(@RequestBody Carro carro);
	
	@GetMapping("/cars/findbyuser/{userId}")
	public List<Carro> findCarrosByUser(@PathVariable("userId")Long id);

}

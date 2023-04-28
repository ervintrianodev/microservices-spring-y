package com.usuario.feign.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.usuario.models.Moto;

@FeignClient(name = "motos-service", url = "http://localhost:8080/api")
public interface IMotoFeignClient {

	@PostMapping("/motos/save")
	public Moto save(@RequestBody Moto moto);

	@GetMapping("/motos/findbyuser/{userId}")
	public List<Moto> findMotosByUser(@PathVariable("userId") Long userId);

}

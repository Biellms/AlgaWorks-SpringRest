package com.algaworks.controller;

import java.util.*;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.algaworks.model.Cliente;
import com.algaworks.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor // Injecao de dependencia atraves do construtor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private ClienteRepository clienteRepository;
	
	//Get All
	@GetMapping
	public List<Cliente> getAll() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/nome/{clienteNome}")
	public List<Cliente> getByNameContaining(@PathVariable String clienteNome) {
		return clienteRepository.findByNomeContaining(clienteNome);
	}
	
	//Get ID
	@GetMapping("/id/{clienteId}")
	public ResponseEntity<Cliente> getId(@PathVariable Long clienteId) {
		return clienteRepository.findById(clienteId)
				.map(ResponseEntity::ok) // .map(cliente -> ResponseEntity.ok(cliente))
					.orElse(ResponseEntity.notFound().build());
		
	//	Optional<Cliente> cliente = clienteRepository.findById(clienteId);
	//		
	//	if (cliente.isPresent()) {
	//		return ResponseEntity.ok(cliente.get());
	//	}
	//		
	//	return ResponseEntity.notFound().build();
	}
}

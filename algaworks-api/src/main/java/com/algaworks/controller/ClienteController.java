package com.algaworks.controller;

import java.util.*;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
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
	
	//Get nome
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
	
	//Post
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente Post(@Valid @RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	//Put
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> Put(@PathVariable Long clienteId, 
			@Valid @RequestBody Cliente cliente) {
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(clienteId);
		cliente = clienteRepository.save(cliente);
		
		return ResponseEntity.ok(cliente);
	}
	
	//Delete
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> Delete(@PathVariable Long clienteId) {
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		clienteRepository.deleteById(clienteId);
		
		return ResponseEntity.noContent().build();
	}
}

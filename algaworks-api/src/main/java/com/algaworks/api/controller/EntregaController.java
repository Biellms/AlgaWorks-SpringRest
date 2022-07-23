package com.algaworks.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.domain.model.Entrega;
import com.algaworks.domain.repository.EntregaRepository;
import com.algaworks.domain.service.EntregaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private EntregaRepository entregaRepository;
	private EntregaService entregaService;

	//Get All
	@GetMapping
	public List<Entrega> getAll() {
		return entregaRepository.findAll();
	}
	
	//Get By Id
	@GetMapping("/{entregaId}")
	public ResponseEntity<Entrega> getById(@PathVariable Long entregaId) {
		return entregaRepository.findById(entregaId)
				.map(ResponseEntity::ok)
					.orElse(ResponseEntity.notFound().build());
	}
	
	//Post
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Entrega Solicitar(@Valid @RequestBody Entrega entrega) {
		
		return entregaService.solicitar(entrega);
	}
	
	//Delete
	@DeleteMapping("/{entregaId}")
	public ResponseEntity<Void> Delete(@PathVariable Long entregaId) {
		if(!entregaRepository.existsById(entregaId)) {
			return ResponseEntity.notFound().build();
		}
		
		entregaService.excluir(entregaId);
		
		return ResponseEntity.noContent().build();
	}
	
}

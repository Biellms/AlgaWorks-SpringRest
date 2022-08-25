package com.algaworks.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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

import com.algaworks.api.assembler.EntregaAssembler;
import com.algaworks.api.representation.DestinatarioRepresentation;
import com.algaworks.api.representation.EntregaRepresentation;
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
	
	/**
	 * Implementando um modelo de representação de entrega 
	 * Utilizando Assembler para montar o retorno, convertendo de um tipo para outro
	 * Utilizando ModelMapper para evitar código boilerplate
	 */
	private EntregaAssembler entregaAssembler;

	@GetMapping
	public List<EntregaRepresentation> getAll() {
		return entregaAssembler.toColletionModel(entregaRepository.findAll());
	}
	
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaRepresentation> getById(@PathVariable Long entregaId) {
		return entregaRepository.findById(entregaId)
				.map(entrega ->  ResponseEntity.ok(entregaAssembler.toModel(entrega)))
					.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaRepresentation Solicitar(@Valid @RequestBody Entrega entrega) {
		Entrega entregaSolicitada = entregaService.solicitar(entrega);
			return entregaAssembler.toModel(entregaSolicitada);
	}

	@DeleteMapping("/{entregaId}")
	public ResponseEntity<Void> Delete(@PathVariable Long entregaId) {
		if(!entregaRepository.existsById(entregaId)) {
			return ResponseEntity.notFound().build();
		}
		
		entregaService.excluir(entregaId);
		
		return ResponseEntity.noContent().build();
	}
	
}

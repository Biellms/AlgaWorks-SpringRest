package com.algaworks.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.api.assembler.EntregaAssembler;
import com.algaworks.api.model.EntregaModel;
import com.algaworks.api.model.input.EntregaInput;
import com.algaworks.domain.model.Entrega;
import com.algaworks.domain.repository.EntregaRepository;
import com.algaworks.domain.service.EntregaService;
import com.algaworks.domain.service.MudarStatusEntregaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private EntregaRepository entregaRepository;
	private EntregaService entregaService;
	private MudarStatusEntregaService mudarStatusEntregaService;
	
	/**
	 * Implementando um modelo de representação de entrega, tanto de saida quanto de entrega
	 * Utilizando Assembler para montar o retorno, convertendo de um tipo para outro
	 * Utilizando ModelMapper para evitar código boilerplate
	 */
	private EntregaAssembler entregaAssembler;

	@GetMapping
	public List<EntregaModel> getAll() {
		return entregaAssembler.toColletionModel(entregaRepository.findAll());
	}
	
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaModel> getById(@PathVariable Long entregaId) {
		return entregaRepository.findById(entregaId)
				.map(entrega ->  ResponseEntity.ok(entregaAssembler.toModel(entrega)))
					.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaModel solicitar(@Valid @RequestBody EntregaInput entregaInput) {
		Entrega novaEntreda = entregaAssembler.toEntity(entregaInput);
		Entrega entregaSolicitada = entregaService.solicitar(novaEntreda);
		
		return entregaAssembler.toModel(entregaSolicitada);
	}
	
	@PutMapping("/{entregaId}/finalizar")
	@ResponseStatus(HttpStatus.OK)
	public EntregaModel finalizar(@PathVariable Long entregaId) {
		Entrega entrega = mudarStatusEntregaService.finalizar(entregaId);
		return entregaAssembler.toModel(entrega);
	}
	
	@PutMapping("/{entregaId}/cancelar")
	@ResponseStatus(HttpStatus.OK)
	public EntregaModel cancelar(@PathVariable Long entregaId) {
		Entrega entrega = mudarStatusEntregaService.cancelar(entregaId);
		return entregaAssembler.toModel(entrega);
	}
	
	@PutMapping("/{entregaId}/reabrir")
	@ResponseStatus(HttpStatus.OK)
	public EntregaModel reabrir(@PathVariable Long entregaId) {
		Entrega entrega = mudarStatusEntregaService.reabrir(entregaId);
		return entregaAssembler.toModel(entrega);
	}

	@DeleteMapping("/{entregaId}")
	public ResponseEntity<Void> delete(@PathVariable Long entregaId) {
		if(!entregaRepository.existsById(entregaId)) {
			return ResponseEntity.notFound().build();
		}
		
		entregaService.excluir(entregaId);
		
		return ResponseEntity.noContent().build();
	}
	
}

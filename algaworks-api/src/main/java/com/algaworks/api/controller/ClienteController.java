package com.algaworks.api.controller;

import java.util.*;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.algaworks.api.assembler.ClienteAssembler;
import com.algaworks.api.model.ClienteModel;
import com.algaworks.api.model.input.ClienteInput;
import com.algaworks.domain.model.Cliente;
import com.algaworks.domain.repository.ClienteRepository;
import com.algaworks.domain.service.ClienteService;

import lombok.AllArgsConstructor;

@AllArgsConstructor // Injecao de dependencia atraves do construtor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private ClienteRepository clienteRepository;
	private ClienteService clienteService;
	
	/**
	 * Implementando um modelo de representação de cliente, tanto de saida quanto de entrega
	 * Utilizando Assembler para montar o retorno, convertendo de um tipo para outro
	 * Utilizando ModelMapper para evitar código boilerplate
	 */
	private ClienteAssembler clienteAssembler;
	
	@GetMapping
	public List<ClienteModel> getAll() {
		return clienteAssembler.toCollectionModel(clienteRepository.findAll());
	}
	
	@GetMapping("/nome/{clienteNome}")
	public List<ClienteModel> getByNameContaining(@PathVariable String clienteNome) {
		return clienteAssembler.toCollectionModel(clienteService.buscarPorNome(clienteNome));
	}
	
	@GetMapping("/id/{clienteId}")
	public ResponseEntity<ClienteModel> getId(@PathVariable Long clienteId) {
		return clienteRepository.findById(clienteId)
				.map(cliente -> ResponseEntity.ok(clienteAssembler.toModel(cliente)))
					.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteModel Post(@Valid @RequestBody ClienteInput clienteInput) {
		Cliente novoCliente = clienteAssembler.toEntity(clienteInput);
		Cliente clienteCadastrado = clienteService.salvar(novoCliente); 
		
		return clienteAssembler.toModel(clienteCadastrado);
	}

	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> Put(@PathVariable Long clienteId, 
			@Valid @RequestBody Cliente cliente) {
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(clienteId);
		cliente = clienteService.salvar(cliente);
		
		return ResponseEntity.ok(cliente);
	}

	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> Delete(@PathVariable Long clienteId) {
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		clienteService.excluir(clienteId);
		
		return ResponseEntity.noContent().build();
	}
}

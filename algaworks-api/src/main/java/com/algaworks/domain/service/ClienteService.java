package com.algaworks.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.domain.exception.DomainException;
import com.algaworks.domain.model.Cliente;
import com.algaworks.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ClienteService {

	private ClienteRepository clienteRepository;

	@Transactional
	public Cliente salvar(Cliente cliente) {
		boolean emailUsage = clienteRepository.findByEmail(cliente.getEmail())
				.stream()
					.anyMatch(clienteExist -> !clienteExist.equals(cliente));
		
		if (emailUsage) {
			throw new DomainException("Já existe um cliente cadastrado com este e-mail!");
		}
				
				
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
}

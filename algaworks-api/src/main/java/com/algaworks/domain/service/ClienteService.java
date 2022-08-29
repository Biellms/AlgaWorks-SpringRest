package com.algaworks.domain.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.api.model.ClienteModel;
import com.algaworks.domain.exception.DomainException;
import com.algaworks.domain.model.Cliente;
import com.algaworks.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ClienteService {

	private ClienteRepository clienteRepository;

	public Cliente buscar(Long clienteId) {
		return clienteRepository.findById(clienteId)
				.orElseThrow(() -> new DomainException("Cliente não encontrado!"));
	}
	
	public List<Cliente> buscarPorNome(String clienteNome) {
		if(clienteRepository.findByNomeContaining(clienteNome).isEmpty()) {
			throw new DomainException("Não existe um cliente com esse nome!");
		}
		
		return clienteRepository.findByNomeContaining(clienteNome);
	}
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		boolean emailUsage = clienteRepository.findByEmail(cliente.getEmail())
				.stream()
					.anyMatch(clienteExist -> !clienteExist.equals(cliente)); // Cliente ja existe ou nao
		
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

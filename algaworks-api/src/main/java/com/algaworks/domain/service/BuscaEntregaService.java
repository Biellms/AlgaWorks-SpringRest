package com.algaworks.domain.service;

import org.springframework.stereotype.Service;

import com.algaworks.domain.exception.NaoEncontradoException;
import com.algaworks.domain.model.Entrega;
import com.algaworks.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {

	private EntregaRepository entregaRepository;
	
	public Entrega buscarEntrega(Long entregaId) {
		return entregaRepository.findById(entregaId)
				.orElseThrow(() -> new NaoEncontradoException("Entrega não encontrada"));
	}
}

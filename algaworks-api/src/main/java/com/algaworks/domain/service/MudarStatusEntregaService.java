package com.algaworks.domain.service;

import org.springframework.stereotype.Service;

import com.algaworks.domain.model.Entrega;
import com.algaworks.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MudarStatusEntregaService {

	private EntregaRepository entregaRepository;
	private BuscaEntregaService buscarEntregaService;
	
	public Entrega finalizar(Long entregaId) {
		Entrega entrega = buscarEntregaService.buscarEntrega(entregaId);
		
		entrega.finalizar();
		
		return entregaRepository.save(entrega);
	}
	
	public Entrega cancelar(Long entregaId) {
		Entrega entrega = buscarEntregaService.buscarEntrega(entregaId);
		
		entrega.cancelar();
		
		return entregaRepository.save(entrega);
	}
	
	public Entrega reabrir(Long entregaId) {
		Entrega entrega = buscarEntregaService.buscarEntrega(entregaId);
		
		entrega.reabrir();
		
		return entregaRepository.save(entrega);
	}
	
}

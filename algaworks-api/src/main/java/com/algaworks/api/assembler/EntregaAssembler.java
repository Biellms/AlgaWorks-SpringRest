package com.algaworks.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaworks.api.representation.EntregaRepresentation;
import com.algaworks.domain.model.Entrega;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EntregaAssembler {

	// Utilizando ModelMapper para evitar código boilerplate
	private ModelMapper modelMapper;
	
	public EntregaRepresentation toModel(Entrega entrega) {
		return modelMapper.map(entrega, EntregaRepresentation.class);
	}
	
	public List<EntregaRepresentation> toColletionModel(List<Entrega> entregas) {
		return entregas.stream()
				.map(this::toModel)
					.collect(Collectors.toList());
	}
}

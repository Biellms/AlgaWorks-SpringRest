package com.algaworks.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaworks.api.model.ClienteModel;
import com.algaworks.api.model.input.ClienteInput;
import com.algaworks.api.model.input.EntregaInput;
import com.algaworks.domain.model.Cliente;
import com.algaworks.domain.model.Entrega;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ClienteAssembler {

	// Utilizando ModelMapper para evitar código boilerplate
	private ModelMapper modelMapper;
	
	public ClienteModel toModel(Cliente cliente) {
		return modelMapper.map(cliente, ClienteModel.class);
	}
	
	public List<ClienteModel> toCollectionModel(List<Cliente> clientes) {
		return clientes.stream()
				.map(this::toModel)
					.collect(Collectors.toList());
	}
	
	public Cliente toEntity(ClienteInput clienteInput) {
		return modelMapper.map(clienteInput, Cliente.class);
	}
}

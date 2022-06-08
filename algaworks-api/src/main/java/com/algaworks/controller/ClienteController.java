package com.algaworks.controller;

import java.util.*;

import javax.persistence.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.model.Cliente;

@RestController
public class ClienteController {
	
	// Interface Jakarta
	@PersistenceContext
	private EntityManager manager;
	
	//Get All
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		return manager.createQuery("from Cliente", Cliente.class)
			.getResultList();
	}
}

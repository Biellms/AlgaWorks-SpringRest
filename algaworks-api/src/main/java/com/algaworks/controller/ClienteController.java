package com.algaworks.controller;

import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.model.Cliente;

@RestController
public class ClienteController {
	
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		
		var c1 = new Cliente();
		c1.setId(1L);
		c1.setNome("Gabriel");
		c1.setEmail("gabriel@email.com");
		c1.setTelefone("11 2222-2222");
		
		var c2 = new Cliente();
		c2.setId(2L);
		c2.setNome("Bruna");
		c2.setEmail("bruna@email.com");
		c2.setTelefone("11 3333-3333");
		
		var c3 = new Cliente();
		c3.setId(3L);
		c3.setNome("Nicoly");
		c3.setEmail("nicoly@email.com");
		c3.setTelefone("11 4444-4444");
		
		return Arrays.asList(c1, c2, c3);
	}
}

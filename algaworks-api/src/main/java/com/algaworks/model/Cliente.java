package com.algaworks.model;

import javax.persistence.*;

import lombok.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true) // Gerar especificamente um ou mais Equals e HashCode
@Getter
@Setter
@Entity
public class Cliente {
	
	@EqualsAndHashCode.Include // EqualsAndHashCode especifico
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String email;
	
	@Column(name = "phone")
	private String telefone;
	
}

package com.algaworks.domain.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.validation.groups.Default;

import com.algaworks.domain.validationgroups.ValidationGroups;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true) // Gerar especificamente um ou mais Equals e HashCode
@Getter
@Setter
@Entity
public class Cliente {
	
	@EqualsAndHashCode.Include // EqualsAndHashCode especifico
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 60)
	private String nome;
	
	@NotBlank
	@Email
	@Size(max = 255)
	private String email;
	
	@NotBlank
	@Size(max = 20)
	@Column(name = "phone")
	private String telefone;
	
}

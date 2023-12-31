package com.algaworks.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DestinatarioModel {

	private String nome;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	
	/*
	 * public String getComplemento() { if(this.complemento == null) { return
	 * "Não possui"; } else { return complemento; } }
	 */
	
}

package com.algaworks.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import lombok.*;

@Getter
@Setter
public class Problem {

	private Integer status;
	private LocalDateTime dataHora;
	private String titulo;
	private List<Campo> campos; // Exibir onde esta o erro
	
	@AllArgsConstructor
	@Getter
	public static class Campo {
		
		private String nome;
		private String mensagem;
	}
}

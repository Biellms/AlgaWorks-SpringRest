package com.algaworks.api.representation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.algaworks.domain.model.StatusEntrega;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaRepresentation {
	
	private Long id;
	private String nomeCliente;
	private DestinatarioRepresentation destinatario;
	private BigDecimal taxa;
	private StatusEntrega status;
	private OffsetDateTime dataPedido;
	private OffsetDateTime dataFinalizacao;
}

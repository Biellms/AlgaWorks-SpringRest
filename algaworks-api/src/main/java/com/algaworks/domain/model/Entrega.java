package com.algaworks.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.algaworks.domain.validationgroups.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/*
 * @Embedded -> Anotação para indicar que é uma relação embutida
 * @Enumerated(EnumType.STRING) -> Indica que o valor de retorno da classe Enum sera uma String
 * @JsonProperty(access = Access.READ_ONLY) -> Anotação para o usuario apenas ler(Get), e não poder modificar o valor(Set) na requisição com Json
 * */
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne // Muitas entregas para um cliente
	private Cliente cliente;
	
	@Embedded
	private Destinatario destinatario;
	
	private BigDecimal taxa;
	
	@Enumerated(EnumType.STRING) 
	private StatusEntrega status;
	
	@Column(name = "data_pedido")
	private OffsetDateTime dataPedido;
	
	@Column(name = "data_finalizacao")
	private OffsetDateTime dataFinalizado;
	
}

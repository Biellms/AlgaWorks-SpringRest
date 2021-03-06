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

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Valid
	@ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
	@NotNull
	@ManyToOne // Muitas entregas para um cliente
	private Cliente cliente;
	
	@Valid
	@NotNull
	@Embedded
	private Destinatario destinatario;
	
	@NotNull
	private BigDecimal taxa;
	
	@JsonProperty(access = Access.READ_ONLY) 
	@Enumerated(EnumType.STRING) 
	private StatusEntrega status;
	
	@JsonProperty(access = Access.READ_ONLY)
	@Column(name = "data_pedido")
	private OffsetDateTime dataPedido;
	
	@JsonProperty(access = Access.READ_ONLY)
	@Column(name = "data_finalizacao")
	private OffsetDateTime dataFinalizado;
	
	/*
	 * @Embedded -> Anotação para indicar que é uma relação embutida
	 * @Enumerated(EnumType.STRING) -> Indica que o valor de retorno da classe Enum sera uma String
	 * @JsonProperty(access = Access.READ_ONLY) -> Anotação para o usuario apenas ler(Get), e não poder modificar o valor(Set) na requisição com Json
	 * */
	
}

package com.vicente.controleponto.api.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@ToString
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "entrada_saida_seq", sequenceName = "entrada_saida_seq", initialValue = 1, allocationSize = 1)
public class EntradaSaida {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entrada_saida_seq")
	private Long id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "registro_id")
	private Registro registro;
	
	@NotNull
	@Temporal(TemporalType.TIME)
	@JsonFormat(pattern = "HH:mm:ss")
	private Date entrada;
	
	@Temporal(TemporalType.TIME)
	@JsonFormat(pattern = "HH:mm:ss")
	private Date saida;

}

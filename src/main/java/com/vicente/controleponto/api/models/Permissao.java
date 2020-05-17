package com.vicente.controleponto.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@SequenceGenerator(name = "permissao_seq", sequenceName = "permissao_seq", allocationSize = 1, initialValue = 1)
public class Permissao {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permissao_seq")
	private Long id;
	
	@NotEmpty(message = "Descrição é obrigatória")
	@Size(min = 3, max = 80)
	private String descricao;

}

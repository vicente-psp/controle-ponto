package com.vicente.controleponto.api.models;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "empresa_seq", sequenceName = "empresa_seq", initialValue = 1, allocationSize = 1)
public class Empresa {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empresa_seq")
	private Long id;
	
	@NotEmpty(message = "Razão social é obrigatório")
	@Size(min = 3, max = 150, message = "Razão social deve ter entre {min} e {max} caracteres")
	private String razaoSocial;
	
	@NotEmpty(message = "CNPJ é obrigatório")
	private String cnpj;

}

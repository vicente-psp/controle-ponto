package com.vicente.controleponto.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "solicitacao_cadastro_seq", sequenceName = "solicitacao_cadastro_seq", initialValue = 1, allocationSize = 1)
public class SolicitacaoCadastro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "solicitacao_cadastro_seq")
	private Long id;
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@NotEmpty
	@Size(min = 50, max = 50)
	private String secretKey;

}

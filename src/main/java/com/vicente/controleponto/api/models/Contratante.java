package com.vicente.controleponto.api.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "contratante_seq", sequenceName = "contratante_seq", initialValue = 1, allocationSize = 1)
public class Contratante {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contratante_seq")
	private Long id;
	
	@NotEmpty
	@Size(min = 4, max = 150)
	private String nome;
	
	@NotNull
	@Size(min = 4, max = 150)
	private String razaoSocial;
	
	@NotEmpty
	@Size(min = 11, max = 14)
	private String cpfCnpj;

	@OneToOne
  @JoinColumn(name = "usuario_id", referencedColumnName = "id")
  private Usuario usuario;
	
	@Column(insertable = false)
	private LocalDateTime dataInclusao;
	
	private LocalDateTime dataAlteracao;
	
	@PreUpdate
	public void preUpdateEntity() {
		dataAlteracao = LocalDateTime.now();
	}

}

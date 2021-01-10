package com.vicente.controleponto.api.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vicente.controleponto.api.models.enums.TipoUsuario;

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
@SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_seq", initialValue = 1, allocationSize = 1)
public class Usuario {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
	private Long id;
	
	@NotEmpty
	@Email
	@Size(min = 4, max = 150)
	private String email;
	
	@Getter(onMethod = @__({@JsonIgnore}))
  @Setter(onMethod = @__({@JsonProperty}))
	@NotEmpty
	@Size(min = 6, max = 200)
	private String senha;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipoUsuario;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_permissao",
		joinColumns = @JoinColumn(name = "usuario_id"),
		inverseJoinColumns = @JoinColumn(name = "permissao_id"))
	private List<Permissao> permissoes;
	
	@Column(insertable = false)
	private LocalDateTime dataInclusao;
	
	private LocalDateTime dataAlteracao;
	
	@Column(insertable = false)
	private Boolean ativo;
	
	@Column(insertable = false)
	private Boolean cadastroAprovado;
	
	@PreUpdate
	public void preUpdateEntity() {
		dataAlteracao = LocalDateTime.now();
	}

}

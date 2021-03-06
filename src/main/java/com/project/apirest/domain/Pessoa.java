package com.project.apirest.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {
	@Id
	@GeneratedValue
	@ApiModelProperty(required = true, example = "")
	private Long id;

	@NotBlank
	@Column(nullable = false)
	@ApiModelProperty(required = true, example = "Matheus")
	private String nome;

	@NotBlank
	@Column(nullable = false)
	@CPF(message = "Ooops ... este cpf não é valido")
	@ApiModelProperty(required = true, example = "170.018.590-01")
	private String cpf;

	@NotNull
	@Column(nullable = false)
	@Past(message = "Ooops ... esta data não é valida")
	@ApiModelProperty(required = true, example = "2000-01-01")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate nascimento;

	@JsonManagedReference
	@NotEmpty(message = "Ooops ... é necessário possuir ao menos um contato")
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, mappedBy = "pessoa")
	private List<Contato> contato;
	
	public Specification<Pessoa> toSpec(){
		return (root, query, builder) -> {
			List<Predicate> predicados = new ArrayList<>();
			
			if(StringUtils.hasText(nome)) {
				Path<String> campoNome = root.<String>get("nome");
				Predicate predicadoNome = builder.like(campoNome, "%"+nome+"%");
				predicados.add(predicadoNome);
			}
			return builder.and(predicados.toArray(new Predicate[0]));
		};
	}
}

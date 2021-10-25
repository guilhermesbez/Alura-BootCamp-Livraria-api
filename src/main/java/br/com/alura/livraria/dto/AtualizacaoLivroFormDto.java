package br.com.alura.livraria.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizacaoLivroFormDto extends LivroFormDto{
	
	@NotNull
	private Long id;
}

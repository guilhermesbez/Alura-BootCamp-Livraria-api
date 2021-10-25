package br.com.alura.livraria.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizacaoAutorFormDto extends AutorFormDto {
	
		@NotNull
		private Long id;
}


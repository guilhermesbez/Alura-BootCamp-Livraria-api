package br.com.alura.livraria.dto;

import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LivroFormDto {
	
	@NotBlank
	@Size(min = 10, max = 200)
	private String titulo;
	
	@PastOrPresent
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataLancamento;
	
	@DecimalMin("100")
	private Integer numeroPaginas;
	
	@JsonAlias("autor_id")
	private Long autorId;
}

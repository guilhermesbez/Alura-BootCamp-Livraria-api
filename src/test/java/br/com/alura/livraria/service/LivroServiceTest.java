package br.com.alura.livraria.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alura.livraria.dto.LivroDto;
import br.com.alura.livraria.dto.LivroFormDto;
import br.com.alura.livraria.repository.AutorRepository;
import br.com.alura.livraria.repository.LivroRepository;

@ExtendWith(MockitoExtension.class)
class LivroServiceTest {

	@Mock
	private LivroRepository repository;
	
	@Mock
	private AutorRepository autorRepository;
	
	@InjectMocks
	private LivroService livroService;
	
	@Test
	void deveriaCadastrarUmLivro() {		
		LivroFormDto livroFormDto = new LivroFormDto(
				"Como fazer amigos e influenciar pessoas", 
				LocalDate.now(), 
				500,
				1l);

		LivroDto livroDto = livroService.cadastrar(livroFormDto);
		
		Mockito.verify(repository.save(Mockito.any()));
		
		assertEquals(livroFormDto.getTitulo(), livroDto.getTitulo());
		assertEquals(livroFormDto.getDataLancamento(), livroDto.getDataLancamento());
		assertEquals(livroFormDto.getNumeroPaginas(), livroDto.getNumeroPaginas());
	}
	
	@Test
	void naoDeveriaCadastrarUmLivroComUsuarioInexistente() {		
		LivroFormDto livroFormDto = new LivroFormDto(
				"Como fazer amigos e influenciar pessoas", 
				LocalDate.now(), 
				500,
				9999l);

		Mockito.when(autorRepository.getById(livroFormDto.getAutorId()))
		.thenThrow(EntityNotFoundException.class);
		
		assertThrows(IllegalArgumentException.class, () -> livroService.cadastrar(livroFormDto));	
	}
}

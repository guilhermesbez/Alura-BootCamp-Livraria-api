package br.com.alura.livraria.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alura.livraria.dto.AutorDto;
import br.com.alura.livraria.dto.AutorFormDto;
import br.com.alura.livraria.repository.AutorRepository;

@ExtendWith(MockitoExtension.class)
class AutorServiceTest {
	
	@Mock
	private AutorRepository repository;
	
	@InjectMocks
	private AutorService autorService;

	@Test
	void deveriaCadastrarAutor() {
		AutorFormDto autorFormDto = new AutorFormDto(
				"Guilherme", 
				"guilherme@email", 
				LocalDate.now(), 
				"Autor de Livros de tecnologia");
		
		AutorDto autorDto = autorService.cadastrar(autorFormDto);
		
		assertEquals(autorFormDto.getNome(), autorDto.getNome());
		assertEquals(autorFormDto.getEmail(), autorDto.getEmail());
	}
}

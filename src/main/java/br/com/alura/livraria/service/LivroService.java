package br.com.alura.livraria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.alura.livraria.dto.LivroDto;
import br.com.alura.livraria.dto.LivroFormDto;
import br.com.alura.livraria.modelo.Livro;

@Service
public class LivroService {

	private List<Livro> livros = new ArrayList<>();
	private ModelMapper modelMapper = new ModelMapper(); 
	
	public List<LivroDto> listar(){
		return livros
				.stream()
				.map(l -> modelMapper.map(l, LivroDto.class))
				.collect(Collectors.toList());	
	}
	
	public void cadastrar(@RequestBody @Valid LivroFormDto dto) {
		Livro livro = modelMapper.map(dto, Livro.class);
		livros.add(livro);
	}
	
	
	
	
	
	
}

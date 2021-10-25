package br.com.alura.livraria.service;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.livraria.dto.AtualizacaoLivroFormDto;
import br.com.alura.livraria.dto.LivroDto;
import br.com.alura.livraria.dto.LivroFormDto;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.modelo.Livro;
import br.com.alura.livraria.repository.AutorRepository;
import br.com.alura.livraria.repository.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository repository;
	
	@Autowired
	private AutorRepository autorRepository;
	
	private ModelMapper modelMapper = new ModelMapper();

	public Page<LivroDto> listar(org.springframework.data.domain.Pageable paginacao) {
		return repository
				.findAll(paginacao)
				.map(l -> modelMapper.map(l, LivroDto.class));
				
	}

	@Transactional
	public LivroDto cadastrar(LivroFormDto dto){
		Long idAutor = dto.getAutorId();
		
		try {
			Autor autor = autorRepository.getById(idAutor);
			Livro livro = modelMapper.map(dto, Livro.class);
			livro.setId(null);
			livro.setAutor(autor);
			
			repository.save(livro);
			
			return modelMapper.map(livro, LivroDto.class);
		}catch (EntityNotFoundException e) {
			throw new IllegalArgumentException("Usuário inexistente");
		}
		
	}
		
	@Transactional
	public LivroDto atualizar(AtualizacaoLivroFormDto dtoAtualizacao) {		
		Livro livro = repository.getById(dtoAtualizacao.getId());
		livro.atualizarInformacoes(dtoAtualizacao.getTitulo(),dtoAtualizacao.getDataLancamento(),dtoAtualizacao.getNumeroPaginas());
		
		return modelMapper.map(livro, LivroDto.class);
	}

	@Transactional
	public void remover(Long id) {
		repository.deleteById(id);
		
	}

	public LivroDto detalhar(Long id) {
		Livro livro = repository
				.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
		
		return modelMapper.map(livro, LivroDto.class);
	}
}

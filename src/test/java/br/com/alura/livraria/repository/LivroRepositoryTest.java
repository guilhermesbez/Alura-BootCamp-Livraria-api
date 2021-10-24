package br.com.alura.livraria.repository;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.alura.livraria.dto.ItemLivroDto;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.modelo.Livro;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
class LivroRepositoryTest {
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private TestEntityManager em;

	@Test
	void deveriaRetornarRelatorioDeLivrosPorAutor() {
		Autor autor = new Autor("Paulo Coelho", "guilherme@email.com", LocalDate.now(), "Autor de livros de tecnologia" );
		em.persist(autor);
		
		Autor autor2= new Autor("Harvei Deitel", "hd@email.com", LocalDate.now(), "Autor de livros tecnicos" );
		em.persist(autor2);
		
		Autor autor3 = new Autor("Tiago Nigro", "tiago@email.com", LocalDate.now(), "Autor de livros sobre finanças" );
		em.persist(autor3);
		
		Livro l1 = new Livro("Java como Programar", LocalDate.now(), 990, autor2);
		em.persist(l1);
		Livro l2 = new Livro(" C: como Programar", LocalDate.now(), 990, autor2);
		em.persist(l2);
		Livro l3 = new Livro("A Bruxa de Portobello", LocalDate.now(), 990, autor);
		em.persist(l3);
		Livro l4 = new Livro(" O Gênio e as Rosas", LocalDate.now(), 990, autor);
		em.persist(l4);
		Livro l5 = new Livro("Manual do Guerreiro da Luz", LocalDate.now(), 990, autor);
		em.persist(l5);
		Livro l6 = new Livro("As Valkírias", LocalDate.now(), 990, autor);
		em.persist(l6);
		Livro l7 = new Livro("Manuscrito Encontrado em Accra", LocalDate.now(), 990, autor);
		em.persist(l7);
		Livro l8 = new Livro("Do Mil ao Milhão", LocalDate.now(), 990, autor3);
		em.persist(l8);
		Livro l9 = new Livro("Método Financeiro Do Primo Rico", LocalDate.now(), 990, autor3);
		em.persist(l9);
		Livro l10 = new Livro("O investidor inteligente", LocalDate.now(), 990, autor3);
		em.persist(l10);
		
		List<ItemLivroDto> relatorio = livroRepository.relatorioLivrosPorAutor();
		Assertions.assertThat(relatorio)
		.hasSize(3)
		.extracting(ItemLivroDto::getTitulo, ItemLivroDto::getQuantidade)
		.containsExactlyInAnyOrder(
				Assertions.tuple("Paulo Coelho", 5l),
				Assertions.tuple("Harvei Deitel", 2l),
				Assertions.tuple("Tiago Nigro", 3l)
				);		
	}
}

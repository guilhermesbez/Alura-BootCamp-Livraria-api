package br.com.alura.livraria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.livraria.dto.ItemLivroDto;
import br.com.alura.livraria.modelo.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

	
	@Query("select new br.com.alura.livraria.dto.ItemLivroDto(" 
			+ "a.nome, "
			+ "count(l.autor)) "
			+ "from Livro l "
			+ "join l.autor a "
			+ "group by l.autor")
	List<ItemLivroDto> relatorioLivrosPorAutor();	
	
	/*
	@Query("select new br.com.alura.carteira.dto.ItemCarteiraDto(" 
			+ "t.ticker, "
			+ "SUM(CASE WHEN(t.tipo = 'COMPRA') THEN t.quantidade ELSE (t.quantidade * -1) END), "
			+ "(SELECT SUM(CASE WHEN(t2.tipo = 'COMPRA') THEN t2.quantidade ELSE (t2.quantidade * -1) END) FROM Transacao t2)) " 
			+ "FROM Transacao t GROUP BY t.ticker")
	 */
}

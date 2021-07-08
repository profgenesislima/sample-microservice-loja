package br.edu.ifpe.gus.graphqlservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpe.gus.graphqlservice.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	Produto findByNome(String nome);

}

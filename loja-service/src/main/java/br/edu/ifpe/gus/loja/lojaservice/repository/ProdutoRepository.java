package br.edu.ifpe.gus.loja.lojaservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpe.gus.loja.lojaservice.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}

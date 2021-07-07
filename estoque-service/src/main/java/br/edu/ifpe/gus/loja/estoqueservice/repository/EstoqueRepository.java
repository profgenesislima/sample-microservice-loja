package br.edu.ifpe.gus.loja.estoqueservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpe.gus.loja.estoqueservice.entity.Estoque;

/**
 * @author G�nesis Lima
 * @email profgenesislima@gmail.com
 *
 */
public interface EstoqueRepository extends JpaRepository<Estoque, Long>{

}

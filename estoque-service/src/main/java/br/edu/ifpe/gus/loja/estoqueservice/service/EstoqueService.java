package br.edu.ifpe.gus.loja.estoqueservice.service;

import java.math.BigDecimal;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpe.gus.loja.estoqueservice.entity.Estoque;
import br.edu.ifpe.gus.loja.estoqueservice.repository.EstoqueRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Gênesis Lima
 * @email profgenesislima@gmail.com
 *
 */
@Service
@Slf4j
public class EstoqueService {

	@Autowired
	EstoqueRepository estoqueRepository;
	
	
	public Estoque getEstoque(Long produtoId) {
		log.info("Buscando estoque do produto: {}",produtoId);
		Optional<Estoque> estoque = estoqueRepository.findById(produtoId);
		return estoque.orElse(Estoque.builder()
				.id(1l)
				.produtoId(produtoId)
				.preco(new BigDecimal("200"))
				.quantidade(2)
				.build());
		
	}
	
	@Transactional
	public Estoque baixaNoEstoque(Estoque estoque) {

		Optional<Estoque> resultsetEstoque = estoqueRepository.findById(estoque.getProdutoId());

		if (resultsetEstoque.isPresent()) {
			var retornoEstoque = resultsetEstoque.get();
			var quantidadeSolicitada = estoque.getQuantidade();
			var quantidadeDoEstoque = retornoEstoque.getQuantidade();

			if (quantidadeSolicitada < quantidadeDoEstoque) {

				retornoEstoque.setQuantidade(quantidadeDoEstoque - quantidadeSolicitada);
				
			}//TODO throw exception in case of quantity is greater than the quantity in stock. 

			return retornoEstoque;
		}

		return null;
	}
	

}

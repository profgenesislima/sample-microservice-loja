package br.edu.ifpe.gus.graphqlservice.mapper;

import java.math.BigDecimal;

import br.edu.ifpe.gus.graphqlservice.dto.ProdutoInputRequest;
import br.edu.ifpe.gus.graphqlservice.entity.Produto;

public class InputRequestToProdutoMapper implements Mapper<ProdutoInputRequest, Produto>{

	@Override
	public Produto map(ProdutoInputRequest produtoInputRequest) {
		
		
		return Produto.builder()
				.nome(produtoInputRequest.getNome())
				.descricao(produtoInputRequest.getDescricao())
				.preco(new BigDecimal(produtoInputRequest.getPreco()))
				.build();
	}

}

package br.edu.ifpe.gus.graphqlservice.resolvers.mutations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import br.edu.ifpe.gus.graphqlservice.dto.ProdutoInputRequest;
import br.edu.ifpe.gus.graphqlservice.entity.Produto;
import br.edu.ifpe.gus.graphqlservice.mapper.InputRequestToProdutoMapper;
import br.edu.ifpe.gus.graphqlservice.repository.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;

@Service
public class ProdutoMutationResolver implements GraphQLMutationResolver{

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto registrarProduto(ProdutoInputRequest produtoInputRequest) {
		
		Produto produto = produtoRepository.findByNome(produtoInputRequest.getNome());
		
		if(produto != null) {
			Produto prod = new InputRequestToProdutoMapper().map(produtoInputRequest);
			return produtoRepository.save(prod);
			
		}
		return produto;
		
	}
}

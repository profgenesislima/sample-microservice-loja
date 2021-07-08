package br.edu.ifpe.gus.graphqlservice.resolvers.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import br.edu.ifpe.gus.graphqlservice.entity.Produto;
import br.edu.ifpe.gus.graphqlservice.repository.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class ProdutoQueryResolver  implements GraphQLQueryResolver{

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Produto> listarProdutos(Integer limit, Integer offset, String orderBy){
		log.info("Listando produtos on class {}", this.getClass().getName());
		PageRequest pageRequest = PageRequest.of(limit, offset, Sort.Direction.DESC, orderBy);
		return produtoRepository.findAll(pageRequest).getContent();
	}
}

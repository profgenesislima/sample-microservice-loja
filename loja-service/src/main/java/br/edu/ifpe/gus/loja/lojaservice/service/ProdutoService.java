package br.edu.ifpe.gus.loja.lojaservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpe.gus.loja.lojaservice.entity.Produto;
import br.edu.ifpe.gus.loja.lojaservice.repository.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	public void registrarProduto(Produto produto) {
		this.produtoRepository.save(produto);
	}


	public Produto pegaPorId(Long produtoId) {
	
		return produtoRepository.findById(produtoId).get();
	}
}

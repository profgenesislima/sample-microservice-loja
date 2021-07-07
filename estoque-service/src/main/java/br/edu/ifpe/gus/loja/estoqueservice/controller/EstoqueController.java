package br.edu.ifpe.gus.loja.estoqueservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.ifpe.gus.loja.estoqueservice.dto.PedidoFormDTO;
import br.edu.ifpe.gus.loja.estoqueservice.dto.PedidoViewDTO;
import br.edu.ifpe.gus.loja.estoqueservice.entity.Estoque;
import br.edu.ifpe.gus.loja.estoqueservice.service.EstoqueService;
import br.edu.ifpe.gus.loja.estoqueservice.utils.EstoqueToPedidoViewDTOMapper;
import br.edu.ifpe.gus.loja.estoqueservice.utils.PedidoFormDTOToEstoqueMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Gênesis Lima
 * @email profgenesislima@gmail.com
 *
 */
@RestController
@Slf4j
public class EstoqueController {

	
	@Autowired
	EstoqueService estoqueService;
	
	@GetMapping("/api/inventario/{produtoId}")
	public @ResponseBody Estoque getEstoquePorProduto(@PathVariable("produtoId") Long produtoId){
		log.info("Requisitando produto no Estoque: {}",produtoId);
		Estoque estoque = estoqueService.getEstoque(produtoId);
		log.info("Estoque: {}",estoque);
		return estoque;
	}
	
	@PostMapping("/api/inventario/pedido")	
	public ResponseEntity<PedidoViewDTO> baixaNoEstoque(@RequestBody PedidoFormDTO pedidoFormDTO, UriComponentsBuilder uriBuilder) {
		var pedido = new PedidoFormDTOToEstoqueMapper().map(pedidoFormDTO); 
		var estoque = estoqueService.baixaNoEstoque(pedido);	
		var estoqueToPedidoViewDTO = new EstoqueToPedidoViewDTOMapper().map(estoque);
        var uri = uriBuilder.path("/api/inventario/{id}").buildAndExpand(estoqueToPedidoViewDTO.getEstoqueId()).toUri();
		return ResponseEntity.created(uri).body(estoqueToPedidoViewDTO);
	}

}

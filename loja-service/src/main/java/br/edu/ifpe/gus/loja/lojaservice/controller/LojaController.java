package br.edu.ifpe.gus.loja.lojaservice.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ifpe.gus.loja.lojaservice.dto.PedidoFormDTO;
import br.edu.ifpe.gus.loja.lojaservice.entity.Pedido;
import br.edu.ifpe.gus.loja.lojaservice.service.ClienteService;
import br.edu.ifpe.gus.loja.lojaservice.service.PedidoService;
import br.edu.ifpe.gus.loja.lojaservice.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Gênesis Lima
 * @email profgenesislima@gmail.com
 *
 */
@RestController
@Slf4j
public class LojaController {

	@Autowired
	ClienteService clienteService;
	
	@Autowired
	PedidoService pedidoService;
	
	@Autowired
	ProdutoService produtoService;
 
	@ApiOperation(value="Efetuar Pedido", response=Boolean.class)
	@ApiResponses(value= {
			@ApiResponse(code=201,message="Pedido Criado com Sucesso."),
			@ApiResponse(code=404, message="Recurso não encontrado")
	})
	@PostMapping("/api/pedidos")
	public ResponseEntity<Pedido> fazerPedido(@RequestBody PedidoFormDTO pedidoFormDTO){
		
		var cliente = clienteService.pegaPorId(pedidoFormDTO.getClienteId()); 
		var produto = produtoService.pegaPorId(pedidoFormDTO.getProdutoId()); 
		
		Pedido pedido = new Pedido().builder()
				.cliente(cliente)
				.produto(produto)
				.quantidade(pedidoFormDTO.getQuantidade())
				.valorTotal(new BigDecimal(pedidoFormDTO.getQuantidade()).multiply(produto.getPreco())).build();
		
		
		pedidoService.registrarPedido(pedido);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	
}

	
	@GetMapping("/api/pedidos")
	public @ResponseBody List<Pedido> listarPedidos(){
		return pedidoService.listarTodos();
	}
	
	public ResponseEntity<Pedido> handleEstoqueFailure(PedidoFormDTO pedidoFormDTO) {
		var cliente = clienteService.pegaPorId(pedidoFormDTO.getClienteId()); 
		var produto = produtoService.pegaPorId(pedidoFormDTO.getProdutoId()); 
		
		Pedido pedido = new Pedido().builder()
				.cliente(cliente)
				.produto(produto)
				.quantidade(pedidoFormDTO.getQuantidade())
				.valorTotal(new BigDecimal(pedidoFormDTO.getQuantidade()).multiply(produto.getPreco())).build();
		log.error("Houve um erro ao invocar o seguinte serviço: ESTOQUE-SERVICE");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	
	@GetMapping("/api/pedidos/{id}")
	public @ResponseBody Pedido listarPedidoPorId(@PathVariable Long id){
		return pedidoService.pegaPedidoPorId(id);
	}
	
	
	
	
}

package br.edu.ifpe.gus.loja.lojaservice.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import br.edu.ifpe.gus.loja.lojaservice.dto.PedidoFormDTO;
import br.edu.ifpe.gus.loja.lojaservice.entity.Cliente;
import br.edu.ifpe.gus.loja.lojaservice.entity.Pedido;
import br.edu.ifpe.gus.loja.lojaservice.entity.Produto;
import br.edu.ifpe.gus.loja.lojaservice.repository.PedidoRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Gênesis Lima
 * @email profgenesislima@gmail.com
 *
 */
@Service
@Slf4j
public class PedidoService {

	@Autowired
    ProdutoService produtoService;
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    @LoadBalanced
    RestTemplate restTemplate;
    
	private Cliente cliente;
	
	private final String GET_PRODUTO_FROM_INVENTARIO ="http://ESTOQUE-SERVICE/estoque/api/inventario/" ;
	private final String POST_BAIXA_NO_ESTOQUE = "http://ESTOQUE-SERVICE/estoque/api/inventario/pedido";
    
    
    public Pedido fazerPedido(Pedido pedido) {
    	
    	
    	PedidoFormDTO map;
    	ObjectMapper mapper = new ObjectMapper();
    	

    	
    	String produtoEstoqueJson = this.restTemplate.getForObject("http://ESTOQUE-SERVICE/estoque/api/inventario/"+1, String.class);
    	
    	System.out.println("PRODUTO ESTOQUE JSON "+produtoEstoqueJson);
    	 try {
			 map = mapper.readValue(produtoEstoqueJson,PedidoFormDTO.class);
			
			log.info("Resultado da chamada ao servico do estoque: {}", map);
			
		} catch (IOException e) {
			log.error("Ocorreu um enquanto tentava obter json. Valor: {} ", produtoEstoqueJson);
			e.printStackTrace();
			return null;
		}
    	 
    	 var qtity = map.getQuantidade();
    	 var produtoId = map.getProdutoId();
    	 var clienteId = map.getClienteId();
    	 var preco = map.getValorTotal();
    	 
    	 System.out.println("Cliente :"+clienteId+". quantidade: "+qtity+". Preco: "+preco);

    	
//    	 if(qtity >=2) {
    		 var produto = new Produto();
    		 produto.setNome("LEGO");
    		 produto.setPreco(new BigDecimal("400.00"));
    		 cliente = new Cliente();
    		 cliente.setNome("João Lima");
    		 cliente.setEmail("joao.lima@email.com");
    		 cliente.setSenha("123123");
    		 pedido = criarPedido(new Produto(), cliente, 10, new BigDecimal("10").multiply(produto.getPreco()));
//    	 }
    	 
    	 log.info("Pedidos {}", pedidoRepository.findAll());
    	 
    	 registrarPedido(pedido);
    	 return pedido;
    }
    
    
    public Pedido criarPedido(Produto produto, Cliente cliente, int
    	    quantidade, BigDecimal valorTotal) {
    	        Pedido pedido = new Pedido();
    	        pedido.setCliente(cliente);
    	        pedido.setProduto(produto);
    	        pedido.setValorTotal(valorTotal);
    	        pedido.setQuantidade(quantidade);;
    	        pedido = pedidoRepository.save(pedido);
    	        return pedido;
    	    }

	@HystrixCommand(fallbackMethod = "handleEstoqueFailure",commandProperties = {
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="3000"),
			@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="2")
			
	})
//	@HystrixCommand(fallbackMethod = "handleEstoqueFailure")
	public void registrarPedido(Pedido pedido) {
		
		pedidoRepository.save(pedido);		

		var headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    
	    var pedidoJsonObject = new JSONObject();
	    //TODO get the properties names using the concepts of introspection and convention over configuration 
	    pedidoJsonObject.put("produtoId", pedido.getProduto().getId());
	    pedidoJsonObject.put("preco", pedido.getProduto().getPreco());
	    pedidoJsonObject.put("quantidade", pedido.getQuantidade());

	    //TODO move all http calls and boilerplates to and specialized gateway class 
		HttpEntity<String> request = new HttpEntity<String>(pedidoJsonObject.toString(), headers);
	    URI locationHeader = restTemplate.postForLocation("http://ESTOQUE-SERVICE/estoque/api/inventario/pedido", request);
	  
	}


	
	public List<Pedido> listarTodos() {
		return this.pedidoRepository.findAll();
	}
	
	public void handleEstoqueFailure(Pedido pedido) {
		log.error("Ocorreu um erro ao acionar o serviço ESTOQUE-SERVICE");
	}


	public Pedido pegaPedidoPorId(Long idPedido) {
		
		return this.pedidoRepository.findById(idPedido).get();
	}
    
}

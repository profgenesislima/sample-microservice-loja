package br.edu.ifpe.gus.loja.estoqueservice.utils;

import java.math.BigDecimal;

import br.edu.ifpe.gus.loja.estoqueservice.dto.PedidoViewDTO;
import br.edu.ifpe.gus.loja.estoqueservice.entity.Estoque;

/**
 * @author Gênesis Lima
 * @email profgenesislima@gmail.com
 *
 */
public class EstoqueToPedidoViewDTOMapper implements Mapper<Estoque,PedidoViewDTO>{

	
	
	@Override
	public PedidoViewDTO map(Estoque estoque) {

		var valorTotal = estoque.getPreco().multiply(new BigDecimal(estoque.getQuantidade()));
		var estoqueCritico = (estoque.getQuantidade() <= 5 ? true: false);
		var pedidoViewDTO = new PedidoViewDTO(estoque.getId(), estoque.getProdutoId(), valorTotal, estoque.getQuantidade(), estoqueCritico,
				estoque.getQuantidade());
		return pedidoViewDTO;
	}

}

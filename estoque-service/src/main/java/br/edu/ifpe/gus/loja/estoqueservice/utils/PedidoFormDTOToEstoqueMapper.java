package br.edu.ifpe.gus.loja.estoqueservice.utils;

import br.edu.ifpe.gus.loja.estoqueservice.dto.PedidoFormDTO;
import br.edu.ifpe.gus.loja.estoqueservice.entity.Estoque;

/**
 * @author Gênesis Lima
 * @email profgenesislima@gmail.com
 *
 */
public class PedidoFormDTOToEstoqueMapper implements Mapper<PedidoFormDTO, Estoque> {

	@Override
	public Estoque map(PedidoFormDTO pedidoFormDTO) {
		var estoque  = new Estoque();
		estoque.setProdutoId(pedidoFormDTO.getProdutoId());
		estoque.setQuantidade(pedidoFormDTO.getQuantidade());
		estoque.setPreco(pedidoFormDTO.getPreco());
		
		return estoque;
	}

}

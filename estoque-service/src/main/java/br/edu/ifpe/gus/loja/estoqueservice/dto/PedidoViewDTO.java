package br.edu.ifpe.gus.loja.estoqueservice.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;


/**
 * @author Gênesis Lima
 * @email profgenesislima@gmail.com
 *
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class PedidoViewDTO {

	private Long estoqueId;
	private Long produtoId;
	private BigDecimal valorTotal;
	private int quantidade;
	private boolean estoqueCritico;
	private int qtdeRestanteEstoque;
}

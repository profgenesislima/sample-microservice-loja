package br.edu.ifpe.gus.loja.estoqueservice.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Gênesis Lima
 * @email profgenesislima@gmail.com
 *
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoFormDTO {

	private Long produtoId;
	private BigDecimal preco;
	private int quantidade;
}

package br.edu.ifpe.gus.loja.lojaservice.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//Data Transfer Object
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoFormDTO {


	private Long clienteId;	
	private Long produtoId;
	private int quantidade;
	private BigDecimal valorTotal;
	
	
		

}

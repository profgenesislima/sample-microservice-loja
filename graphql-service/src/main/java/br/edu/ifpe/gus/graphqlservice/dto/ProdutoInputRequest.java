package br.edu.ifpe.gus.graphqlservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoInputRequest {
	
	private String nome;
	private String descricao;
	private String preco;
	
}

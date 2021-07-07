package br.edu.ifpe.gus.graphqlservice.dto;

import lombok.Data;

@Data
public class PostInputRequest {
	
	String title;
	String content;
	Long authorId;

}

package br.edu.ifpe.gus.graphqlservice.mapper;

public interface Mapper<S,T> {

	T map(S s);
	
}

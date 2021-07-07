package br.edu.ifpe.gus.loja.estoqueservice.utils;

/**
 * @author Gênesis Lima
 * @email profgenesislima@gmail.com
 *
 * @param <S>
 * @param <T>
 */
public interface Mapper<S,T> {

	T map(S s);
}

package br.edu.ifpe.gus.graphqlservice.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;

@Configuration
public class GraphQLConfig {

	@Bean
	public GraphQLErrorHandler errorHandler() {
		return new GraphQLErrorHandler() {
			
			@Override
			public List<GraphQLError> processErrors(List<GraphQLError> graphqlErrors) {
				List<GraphQLError> listOfErrors = graphqlErrors.stream()
												  .filter(e -> isClientError(e))
												  .collect(Collectors.toList());
				
				List<GraphQLError> serverErrors = graphqlErrors.stream()
                        .filter(e -> !isClientError(e))
                        .collect(Collectors.toList());
				
				return graphqlErrors;
			}
		};
	}

	 boolean isClientError(GraphQLError graphqlerror) {
		
		return !(graphqlerror instanceof ExceptionWhileDataFetching ||
				graphqlerror instanceof Throwable);
	}
}

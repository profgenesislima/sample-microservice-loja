package br.edu.ifpe.gus.loja.estoqueservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * @author Gênesis Lima
 * @email profgenesislima@gmail.com
 *
 */
@SpringBootApplication
public class EstoqueServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstoqueServiceApplication.class, args);
	}

}

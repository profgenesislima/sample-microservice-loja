package br.edu.ifpe.gus.loja.discoveryservice;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Gênesis Lima
 * @email profgenesislima@gmail.com
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServiceApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(DiscoveryServiceApplication.class)
		.web(WebApplicationType.SERVLET).run(args); 
	}

}

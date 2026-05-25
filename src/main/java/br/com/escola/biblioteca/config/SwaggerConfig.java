package br.com.escola.biblioteca.config;

<<<<<<< HEAD
=======

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
>>>>>>> bc38e10307b3d29d6b1d4da34e93e060820d3d52
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {
<<<<<<< HEAD

    @Bean
    public OpenAPI bibliotecaAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("API Biblioteca")
                        .version("1.0")
                        .description("Catálogo de livros e autores"));

    }
=======
	
	@Value("${dominio.openapi.dev-url}")
	private String devUrl;
	@Value("${dominio.openapi.prod-url}")
	private String prodUrl;
	@Bean
	public OpenAPI bibliotecaAPI() {
	Server devServer = new Server();
	devServer.setUrl(devUrl);
	devServer.setDescription("URL do servidor de desenvolvimento");
	Server prodServer = new Server();
	prodServer.setUrl(prodUrl);
	prodServer.setDescription("URL do servidor de produção");
	Contact contact = new Contact();
	contact.setEmail("biblioteca@api.com.br");
	contact.setName("Biblioteca");
	contact.setUrl("https://www.minhabiblioteca.com.br");
	License apacheLicense = new License().name("ApacheLicense").url("https://www.apache.org/licenses/LICENSE-2.0");
	Info info = new Info().title("API Biblioteca").version("1.0").contact(contact)
	.description("Catálogo de livros e autores").termsOfService("https://www.minhabiblioteca.com.br/termos")
	.license(apacheLicense);
	return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
	}
>>>>>>> bc38e10307b3d29d6b1d4da34e93e060820d3d52
}

package br.com.escola.biblioteca.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = {
    @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
    @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
    @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
    @ApiResponse(responseCode = "500", description = "Exceção interna da aplicação")
})

public @interface ApiRespostasPadrao {
}
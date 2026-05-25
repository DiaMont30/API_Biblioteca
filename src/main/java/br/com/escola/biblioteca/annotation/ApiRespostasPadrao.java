package br.com.escola.biblioteca.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.escola.biblioteca.exception.ErroResposta;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)

@ApiResponses(value = {
        @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroResposta.class), examples = @ExampleObject(value = SwaggerExemplos.ERRO_403))),
        @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroResposta.class), examples = @ExampleObject(value = SwaggerExemplos.ERRO_404))),
        @ApiResponse(responseCode = "500", description = "Exceção interna da aplicação", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroResposta.class), examples = @ExampleObject(value = SwaggerExemplos.ERRO_500)))
})

public @interface ApiRespostasPadrao {
}
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
        @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroResposta.class), examples = @ExampleObject(value = """
                {
                    "status": 403,
                    "titulo": "Acesso Negado",
                    "dataHora": "25/05/2026 18:57:53",
                    "erros": [
                        "Seu perfil não tem permissão para realizar esta ação."
                    ]
                }
                """))),

        @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroResposta.class), examples = @ExampleObject(value = """
                    {
                        "status": 404,
                        "titulo": "Erro na validação de dados",
                        "dataHora": "25/05/2026 18:57:53",
                        "erros": [
                            "Livro não encontrado com o id: 3"
                        ]
                    }
                """))),
        @ApiResponse(responseCode = "500", description = "Exceção interna da aplicação", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroResposta.class), examples = @ExampleObject(value = """
                {
                    "status": 500,
                    "titulo": "Erro Interno do Servidor",
                    "dataHora": "25/05/2026 18:57:53",
                    "erros": [
                        "Ocorreu um erro inesperado. Contate o suporte."
                    ]
                }
                """)))
})

public @interface ApiRespostasPadrao {
}
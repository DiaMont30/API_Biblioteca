package br.com.escola.biblioteca.annotation;

public interface SwaggerExemplos {

    String ERRO_403 = """
            {
                "status": 403,
                "titulo": "Acesso Negado",
                "dataHora": "25/05/2026 18:57:53",
                "erros": [
                    "Seu perfil não tem permissão para realizar esta ação."
                ]
            }
            """;

    String ERRO_404 = """
            {
                "status": 404,
                "titulo": "Erro na validação de dados",
                "dataHora": "25/05/2026 18:57:53",
                "erros": [
                    "Recurso não encontrado com o id especificado."
                ]
            }
            """;

    String ERRO_500 = """
            {
                "status": 500,
                "titulo": "Erro Interno do Servidor",
                "dataHora": "25/05/2026 18:57:53",
                "erros": [
                    "Ocorreu um erro inesperado. Contate o suporte."
                ]
            }
            """;
}
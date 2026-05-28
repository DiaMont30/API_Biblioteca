package br.com.escola.biblioteca.dto;

import br.com.escola.biblioteca.enums.SiglaGenero;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto de resposta com os dados detalhados do livro")
public record LivroResponseDTO(
		
		@Schema(description="Identificador unico do livro", example="1")
        Long id,
        
    	@Schema(description = "Titulo do livro", example = "O Pequeno Principe", requiredMode = Schema.RequiredMode.REQUIRED)
        String titulo,
        
    	@Schema(description = "ISBN do livro no formato 978-3-16-148410-0", example = "978-85-333-0227-3")
        String isbn,
        
    	@Schema(description = "Ano em que o livro foi publicado", example = "2024")
        Integer anoPublicacao,
        
    	@Schema(description = "ID dogênero vinculado a este livro", example = "1")
        Long generoId, 
        
    	@Schema(description = "A sigla do gênero do livro", example = "FIC")
        SiglaGenero generoSigla,
        
        @Schema(description = "ID do autor vinculado a este livro", example = "1")
        Long autorId,
        
        @Schema(description = "Nome do autor vinculado a este livro", example = "João da Silva")
        String autorNome, 
        
        @Schema(description = "ID da editora vinculado a este livro", example = "1")
        Long editoraId,
        
        @Schema(description = "Nome da editora vinculada a este livro", example = "Vozes")
        String editoraNome) {
}

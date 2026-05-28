package br.com.escola.biblioteca.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Representação de dados para cadastro e atualização de um livro")
public record LivroRequestDTO(
		
        @NotBlank(message = "O nome do livro é obrigatório")
        @Size(max=60, message="Campo não pode exceder 60 de tamanho")
    	@Schema(description = "Titulo do livro", example = "O Pequeno Principe", requiredMode = Schema.RequiredMode.REQUIRED)
        String titulo,
        
        @Size(max=17, message="Campo não pode exceder 17 de tamanho")
    	@Schema(description = "ISBN do livro no formato 978-3-16-148410-0", example = "978-85-333-0227-3")
        String isbn,
        
    	@Schema(description = "Ano em que o livro foi publicado", example = "2024")
        Integer anoPublicacao,
        
        @NotNull(message = "O id do gênero é obrigatório")
        @Schema(description = "ID do gênero previamente cadastrado", example = "1")
        Long generoId,
        
        @NotNull(message = "O id do autor é obrigatório")
        @Schema(description = "ID do autor previamente cadastrado", example = "1")
        Long autorId,
        
        @NotNull(message = "O id da editora é obrigatório")
        @Schema(description = "ID da editora previamente cadastrada", example = "1")
        Long editoraId) {

}

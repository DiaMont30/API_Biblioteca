package br.com.escola.biblioteca.dto;

import java.time.LocalDate;
import java.util.List;

import br.com.escola.biblioteca.entity.Livro;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto de resposta com os dados detalhados do autor")
public record AutorResponseDTO(
		
		@Schema(description="Identificador unico do autor", example="1")
        Long id,
        
        @Schema(description = "Nome do autor", example = "João da Silva", requiredMode = Schema.RequiredMode.REQUIRED)
        String nome,
        
    	@Schema(description="Nacionalidado do autor", example="Brasileiro")
        String nacionalidade,
        
    	@Schema(description="Data de nascimento do autor", example="2000-04-04")
        LocalDate dataNascimento,
        
    	@Schema(description="Lista de livros do autor")
        List<Livro> livros) {
}

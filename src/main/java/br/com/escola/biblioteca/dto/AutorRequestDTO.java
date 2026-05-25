package br.com.escola.biblioteca.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Representação de dados para cadastro e atualização de um autor")
public record AutorRequestDTO(
	
	
    @NotBlank(message = "O nome do autor é obrigatório")
    @Size(max = 60, message = "Campo não pode exceder 60 de tamanho")
    @Schema(description = "Nome do autor", example = "João da Silva", requiredMode = Schema.RequiredMode.REQUIRED)
    String nome,
    
    @Size(max=20, message="Campo não pode exceder 20 de tamanho")
    @Schema(description="Nacionalidado do autor", example="Brasileiro")
    String nacionalidade,
    
    @Schema(description="Data de nascimento do autor", example="2000-04-04")
    LocalDate dataNascimento
) {}
   
package br.com.escola.biblioteca.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AutorRequestDTO(
		
    @NotBlank(message = "O nome do autor é obrigatório")
    @Size(max = 60, message = "Tamanho máximo 60")
    String nome,
    String nacionalidade,
    LocalDate dataNascimento
) {}
    


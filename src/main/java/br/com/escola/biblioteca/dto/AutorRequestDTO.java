package br.com.escola.biblioteca.dto;

import java.time.LocalDate;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AutorRequestDTO(
		
    @NotBlank(message = "O nome do autor é obrigatório")
    @Size(max = 60, message = "Campo não pode exceder 60 de tamanho")
    String nome,
    @Size(max=20, message="Campo não pode exceder 20 de tamanho")
    String nacionalidade,
    LocalDate dataNascimento
) {}
   
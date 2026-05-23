package br.com.escola.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LivroRequestDTO(
        @NotBlank String titulo,
        String isbn,
        Integer anoPublicacao,
        String genero,
        @NotNull Long autorId) {

}

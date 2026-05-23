package br.com.escola.biblioteca.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LivroRequestDTO(
        @NotBlank(message = "O nome do livro é obrigatório")
        String titulo,
        String isbn,
        Integer anoPublicacao,
        String genero,
        @NotNull(message = "O id do autor precisa existir")
        Long autorId) {

}

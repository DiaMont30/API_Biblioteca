package br.com.escola.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LivroRequestDTO(
        @NotBlank(message = "O nome do livro é obrigatório")
        @Size(max=30, message="Campo não pode exceder 30 de tamanho")
        String titulo,
        @Size(max=17, message="Campo não pode exceder 17 de tamanho")
        String isbn,
        Integer anoPublicacao,
        String genero,
        @NotNull(message = "O id do autor é obrigatório")
        Long autorId) {

}

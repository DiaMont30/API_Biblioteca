package br.com.escola.biblioteca.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record AutorRequestDTO(
        @NotNull Long Id,
        String nome,
        String nacionalidade,
        LocalDate dataNascimento) {
}

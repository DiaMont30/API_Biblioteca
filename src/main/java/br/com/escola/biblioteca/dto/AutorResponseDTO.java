package br.com.escola.biblioteca.dto;

import java.time.LocalDate;
import java.util.List;

import br.com.escola.biblioteca.entity.Livro;

public record AutorResponseDTO(
        Long id,
        String nome,
        String nacionalidade,
        LocalDate dataNascimento,
        List<Livro> livros) {
}

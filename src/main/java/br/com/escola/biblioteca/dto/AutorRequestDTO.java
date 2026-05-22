package br.com.escola.biblioteca.dto;

import java.time.LocalDate;

public record AutorRequestDTO(
    String nome,
    String nacionalidade,
    LocalDate dataNascimento
) {}
    


package br.com.escola.biblioteca.dto;

import java.time.LocalDate;

public record AutorResponseDTO (    
    Long id,
    String nome,
    String nacionalidade,
    LocalDate dataNascimento
){}
    
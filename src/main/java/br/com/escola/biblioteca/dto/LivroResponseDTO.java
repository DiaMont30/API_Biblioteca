package br.com.escola.biblioteca.dto;

public record LivroResponseDTO (
    Long id,
    String titulo,
    String istn,
    Integer anoPublicacao,
    String genero,
    Long autorId,
    String autorNome
){}

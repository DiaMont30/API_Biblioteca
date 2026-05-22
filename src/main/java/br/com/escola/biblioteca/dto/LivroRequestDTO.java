package br.com.escola.biblioteca.dto;

public record LivroRequestDTO(
    String titulo,
    String istn,
    Integer anoPublicacao,
    String genero,
    Long autorId
) {}



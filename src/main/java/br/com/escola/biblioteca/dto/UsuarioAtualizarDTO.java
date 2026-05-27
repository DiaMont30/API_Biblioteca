package br.com.escola.biblioteca.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioAtualizarDTO(
    @NotBlank String nome,
    @NotBlank @Email String email) {
}
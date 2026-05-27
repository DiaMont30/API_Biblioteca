package br.com.escola.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;

public record AlterarSenhaDTO(
    @NotBlank String senhaAntiga,
    @NotBlank String novaSenha) {
}
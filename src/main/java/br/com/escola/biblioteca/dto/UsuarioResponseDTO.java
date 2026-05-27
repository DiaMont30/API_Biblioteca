package br.com.escola.biblioteca.dto;

import br.com.escola.biblioteca.enums.UsuarioRole;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        UsuarioRole role) {

}

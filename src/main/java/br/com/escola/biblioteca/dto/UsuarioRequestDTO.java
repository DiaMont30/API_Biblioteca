package br.com.escola.biblioteca.dto;

import br.com.escola.biblioteca.enums.UsuarioRole;

public record UsuarioRequestDTO(
        String nome,
        String email,
        String senha,
        UsuarioRole role) {

}

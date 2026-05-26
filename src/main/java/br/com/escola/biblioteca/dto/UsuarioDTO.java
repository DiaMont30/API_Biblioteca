package br.com.escola.biblioteca.dto;

import br.com.escola.biblioteca.enums.UsuarioRole;

public record UsuarioDTO(String login, String senha, UsuarioRole role) {

}

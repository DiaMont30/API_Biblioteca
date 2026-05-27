package br.com.escola.biblioteca.dto;

import br.com.escola.biblioteca.enums.EstadoBrasileiro;
import io.swagger.v3.oas.annotations.media.Schema;

public record EditoraResponseDTO(
        @Schema(description = "Identificador unico da editora", example = "1") Long id,

        @Schema(description = "Nome da editora que detem os direitos de publicação dos livros", example = "Editora Vozes") String nome,

        @Schema(description = "CNPJ da editora", example = "00.000.000/0000-00") String cnpj,

        @Schema(description = "Estado onde a editora está localizada", example = "SP") EstadoBrasileiro estado) {

}

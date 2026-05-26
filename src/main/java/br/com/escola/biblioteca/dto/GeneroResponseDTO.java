package br.com.escola.biblioteca.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record GeneroResponseDTO(
                @Schema(description = "Identificador unico da editora", example = "1") Long id,

                @Schema(description = "Nome da editora", example = "Editora Vozes") String nome,

                @Schema(description = "Sigla da editora", example = "ROM") String sigla) {
}

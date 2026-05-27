package br.com.escola.biblioteca.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record GeneroResponseDTO(
                @Schema(description = "Identificador unico do gênero", example = "1") Long id,

                @Schema(description = "Nome do Gênero", example = "Romance Literário") String nome,

                @Schema(description = "Sigla do gênero", example = "ROM") String sigla) {
}

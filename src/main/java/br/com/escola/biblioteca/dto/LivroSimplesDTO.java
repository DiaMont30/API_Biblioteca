package br.com.escola.biblioteca.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record LivroSimplesDTO (
		@Schema(example = "1") Long id,
        @Schema(example = "Dom Casmurro") String titulo,
        @Schema(example = "978-85-333-0227-1") String isbn,
        @Schema(example = "1899") Integer anoPublicacao	){
}

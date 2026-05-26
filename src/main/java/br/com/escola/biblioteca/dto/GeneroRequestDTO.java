package br.com.escola.biblioteca.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record GeneroRequestDTO(

        @NotBlank(message = "O nome do gênero é obrigatório e não pode ser vazio") @Schema(description = "Nome do gênero literário", example = "Ficção Científica", requiredMode = Schema.RequiredMode.REQUIRED) String nome,

        @NotBlank(message = "A sigla do gênero é obrigatória e não pode ser vazio") @Schema(description = "Sigla do gênero literário", example = "FC", requiredMode = Schema.RequiredMode.REQUIRED) String sigla) {

}

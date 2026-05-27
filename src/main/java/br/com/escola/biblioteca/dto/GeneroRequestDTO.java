package br.com.escola.biblioteca.dto;

import br.com.escola.biblioteca.enums.SiglaGenero;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GeneroRequestDTO(

        @NotBlank(message = "O nome do gênero é obrigatório e não pode ser vazio") @Schema(description = "Nome do gênero literário", example = "Ficção Científica", requiredMode = Schema.RequiredMode.REQUIRED) String nome,

        @NotNull(message = "A sigla do gênero é obrigatória e não pode ser vazio") @Schema(description = "Sigla do gênero literário", example = "FICCAO", requiredMode = Schema.RequiredMode.REQUIRED) SiglaGenero sigla) {

}

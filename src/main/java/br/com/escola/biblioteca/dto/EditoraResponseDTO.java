package br.com.escola.biblioteca.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto de resposta com os dados detalhados da editora")
public record EditoraResponseDTO(
                @Schema(description = "Identificador unico da editora", example = "1") Long id,

                @Schema(description = "Nome da editora que detem os direitos de publicação dos livros", example = "Editora Vozes") String nome,

                @Schema(description = "CNPJ da editora", example = "00.000.000/0000-00") String cnpj,

                @Schema(description = "Estado onde a editora está localizada", example = "SP") String estado) {

}

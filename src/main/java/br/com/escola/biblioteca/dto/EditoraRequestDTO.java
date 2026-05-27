package br.com.escola.biblioteca.dto;

import br.com.escola.biblioteca.enums.EstadoBrasileiro;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Representação do objeto de requisição para cadastrar ou atualizar uma editora")
public record EditoraRequestDTO(

                @NotBlank(message = "Não é possível cadastrar uma editora sem nome") @Schema(description = "Nome da editora que detem os direitos de publicação dos livros", example = "Editora Vozes", requiredMode = Schema.RequiredMode.REQUIRED) String nome,

                @NotBlank(message = "Não é possível cadastrar uma editora sem informar o cnpj") @Size(max = 18, message = "Este campo precisa respeitar o formato de cnpj: 00.000.000/0000-00") @Schema(description = "CNPJ da editora", example = "00.000.000/0000-00", requiredMode = Schema.RequiredMode.REQUIRED) String cnpj,

        @NotBlank(message = "Não é possível cadastrar uma editora sem informar o estado") @Schema(description = "Estado onde a editora está localizada", example = "SP", requiredMode = Schema.RequiredMode.REQUIRED) EstadoBrasileiro estado) {

}

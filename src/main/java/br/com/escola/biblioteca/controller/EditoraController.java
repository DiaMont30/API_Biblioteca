package br.com.escola.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.biblioteca.annotation.ApiRespostasPadrao;
import br.com.escola.biblioteca.dto.EditoraRequestDTO;
import br.com.escola.biblioteca.dto.EditoraResponseDTO;
import br.com.escola.biblioteca.service.EditoraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/Editora")
@ApiRespostasPadrao 
@Tag(name = "Editora", description = "Endpoints para gerenciamento de editora")
public class EditoraController {

    @Autowired
    private EditoraService editoraService;
    
    @GetMapping("/todas-as-editoras")
    @Operation (summary = "LIstar todas as editoras", description = "A resposta lista os dados de todas as editoras") 
    @ApiResponse(responseCode = "200", description = "Retorna todas as editoras")
    public ResponseEntity<List<EditoraResponseDTO>> getAllEditora() {
        return ResponseEntity.ok(editoraService.listarEditoras());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Lista uma editora de id específico", description = "A resposta lista os dados de uma única editora do id informado: id, nome, cnpj e estado.")
    @ApiResponse(responseCode = "200", description = "Retorna a editora especificada")
    public ResponseEntity<EditoraResponseDTO> getEditoraByid(@PathVariable Long id) {
        return ResponseEntity.ok(editoraService.obterEditoraPorId(id));
    }
    
    @PostMapping("/adicionar-editora")
    @Operation(summary = "Cadastrar Editora", description = "Insere uma nova editora na base de dados e retorna o objeto criado com seu ID gerado.")
    @ApiResponse(responseCode = "201", description = "Editora criada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos enviados no corpo da requisição", content = @Content)
    public ResponseEntity<EditoraResponseDTO> adicionarEditora(@Valid @RequestBody EditoraRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(editoraService.salvar(dto));
    }
    
    @PutMapping("/atualizar-editora/{id}")
   @Operation(summary = "Atualizar Editora", description = "Atualiza dados de uma editora")
   @ApiResponse(responseCode = "200", description = "Editora Atualizada")
@ApiResponse(responseCode="400", description = "Dados inválidos enviados no corpo da requisição", content = @Content) 
public ResponseEntity<EditoraResponseDTO> atualizarEditora(@PathVariable Long id, @Valid @RequestBody EditoraRequestDTO dto) {
    return ResponseEntity.ok(editoraService.atualizar(id, dto));
}

@DeleteMapping("/deletar-editora/{id}")
@Operation(summary = "Remover Editora", description = "Remove uma editora permanentemente da base de dados")
@ApiResponse(responseCode = "204", description = "Editora Removida com Sucesso")
public ResponseEntity<Void> deletarEditora(@PathVariable Long id) { 
    editoraService.deletar(id);
    return ResponseEntity.noContent().build();

}


    }
    
    
    


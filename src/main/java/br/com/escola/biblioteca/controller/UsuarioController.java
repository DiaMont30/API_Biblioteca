package br.com.escola.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.biblioteca.annotation.ApiRespostasPadrao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.escola.biblioteca.dto.AlterarSenhaDTO;
import br.com.escola.biblioteca.dto.UsuarioAtualizarDTO;
import br.com.escola.biblioteca.dto.UsuarioResponseDTO;
import br.com.escola.biblioteca.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@ApiRespostasPadrao
@Tag(name = "Usuario", description = "Endpoints para gerenciamento de usuário")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/todos-os-usuarios")
    @Operation(summary = "Lista todos os usuários", description = "A resposta lista os dados dos usuários: id, nome, email.")
    @ApiResponse(responseCode = "200", description = "Retorna todos os usuários")
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lista um usuário de id específico", description = "A resposta lista os dados de um único usuário do id informado: id, nome, email.")
    @ApiResponse(responseCode = "200", description = "Retorna o usuário especificado")
    public ResponseEntity<UsuarioResponseDTO> listarUsuarioPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.listarUsuarioPorId(id));
    }

    @PutMapping("/atualizar-usuario/{id}")
    @Operation(summary = "Atualizar Usuário", description = "Atualiza dados de um usuário")
    @ApiResponse(responseCode = "400", description = "Dados inválidos enviados no corpo da requisição", content = @Content)
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    public ResponseEntity<UsuarioResponseDTO> atualizar(@PathVariable Long id,
            @Valid @RequestBody UsuarioAtualizarDTO dto) {
        return ResponseEntity.ok(usuarioService.atualizar(id, dto));
    }

    @PatchMapping("/alterar-senha/{id}")
    @Operation(summary = "Alterar Senha", description = "Exige a senha antiga para cadastrar uma nova")
    @ApiResponse(responseCode = "200", description = "Senha alterada com sucesso")
    @ApiResponse(responseCode = "403", description = "Senha antiga incorreta", content = @Content)
    public ResponseEntity<Void> alterarSenha(
            @PathVariable Long id,
            @Valid @RequestBody AlterarSenhaDTO dto) {

        usuarioService.alterarSenha(id, dto.senhaAntiga(), dto.novaSenha());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deletar-usuario/{id}")
    @Operation(summary = "Deletar Usuário", description = "Deleta um usuário do sistema")
    @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
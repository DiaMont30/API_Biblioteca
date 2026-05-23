package br.com.escola.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.biblioteca.dto.LivroRequestDTO;
import br.com.escola.biblioteca.dto.LivroResponseDTO;
import br.com.escola.biblioteca.entity.Autor;
import br.com.escola.biblioteca.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/Livro")
public class LivroController {

	@Autowired
	private LivroService livroService;

	@GetMapping("/todos-os-livros")
	@Operation(summary = "Lista todos os livros", description = "A resposta lista os dados dos livros: id, titulo, isbn, ano publicacao, genero, autor id e autor nome.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna todos os livros", content = {
					@Content(schema = @Schema(implementation = Autor.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "500", description = "Exceção interna da aplicação") })
	public ResponseEntity<List<LivroResponseDTO>> getallLivro() {
		return ResponseEntity.ok(livroService.listarLivros());
	}

	@GetMapping("/{id}")
    @Operation(
    	    summary = "Lista um livro de id específico", 
    	    description = "A resposta lista os dados de um único livro do id informado: id, titulo, isbn, ano publicacao, genero, autor id e autor nome."
    	)
    	@ApiResponses(value = {
    	    @ApiResponse(responseCode = "200", description = "Retorna o livro especificado",
    	        content = {@Content(schema = @Schema(implementation = Autor.class), mediaType = "application/json")}
    	    ),
    	    @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
    	    @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
    	    @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
    	    @ApiResponse(responseCode = "500", description = "Exceção interna da aplicação") 
    	})
	public ResponseEntity<LivroResponseDTO> getLivrosByid(@PathVariable Long id) {
		return ResponseEntity.ok(livroService.obterLivroPorId(id));
	}

	@PostMapping("adicionar-livro")
    @Operation(
    	    summary = "Cadastrar Livro", 
    	    description = "Insere um novo livro na base de dados e retorna o objeto criado com seu ID gerado."
    	)
    	@ApiResponses(value = {
    	    @ApiResponse(
    	        responseCode = "201", 
    	        description = "Livro criado com sucesso",
    	        content = { @Content(schema = @Schema(implementation = Autor.class), mediaType = "application/json") }
    	    ),
    	    @ApiResponse(responseCode = "400", description = "Dados inválidos enviados no corpo da requisição"),
    	    @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
    	    @ApiResponse(responseCode = "500", description = "Erro interno ao processar o cadastro")
    	})
	public ResponseEntity<LivroResponseDTO> adicionarLivro(@Valid @RequestBody LivroRequestDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(livroService.salvar(dto));

	}

	@PutMapping("/atualizar-livro/{id}")
    @Operation(
    	    summary = "Atualizar Livro", 
    	    description = "Atualiza dados de um livro"
    	)
    	@ApiResponses(value = {
    	    @ApiResponse(responseCode = "200", description = "Livro Atualizado"),
    	    @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
    	    @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
    	    @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
    	    @ApiResponse(responseCode = "500", description = "Exceção interna da aplicação")
    	})
	public ResponseEntity<LivroResponseDTO> atualizarLivro(@PathVariable Long id,
			@Valid @RequestBody LivroRequestDTO dto) {
		return ResponseEntity.ok(livroService.atualizar(id, dto));
	}

	@DeleteMapping("/deletar-livro/{id}")
	 @Operation(
	    	    summary = "Remover Livro", 
	    	    description = "Remove um livro permanentemente da base de dados"
	    	)
	    	@ApiResponses(value = {
	    	    @ApiResponse(responseCode = "204", description = "Livro Removido com Sucesso (Sem conteúdo de retorno)"),
	    	    @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
	    	    @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
	    	    @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
	    	    @ApiResponse(responseCode = "500", description = "Exceção interna da aplicação")
	    	})
	public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {
		livroService.deletar(id);
		return ResponseEntity.noContent().build();
	}

}
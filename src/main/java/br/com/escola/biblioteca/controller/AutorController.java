package br.com.escola.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.biblioteca.annotation.ApiRespostasPadrao;
import br.com.escola.biblioteca.dto.AutorRequestDTO;
import br.com.escola.biblioteca.dto.AutorResponseDTO;
import br.com.escola.biblioteca.service.AutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Autor")
@ApiRespostasPadrao
@Tag(name = "Autor", description = "Endpoints para gerenciamento de autor")
public class AutorController {

	@Autowired
	private AutorService autorService;

	@GetMapping("/todos-os-autores")
	@Operation(summary = "Lista todos os autores", description = "A resposta lista os dados dos autores: id, nome, nacionalidade, data de nascimento e lista de livros do autor.")
	@ApiResponse(responseCode = "200", description = "Retorna todos os autores")
	public ResponseEntity<List<AutorResponseDTO>> getAllAutor() {
		return ResponseEntity.ok(autorService.listarAutores());

	}

	@GetMapping("/{id}")
	@Operation(summary = "Lista um autor de id específico", description = "A resposta lista os dados de um único autor do id informado: id, nome, nacionalidade, data de nascimento e lista de livros do autor.")
	@ApiResponse(responseCode = "200", description = "Retorna o autor especificado")
	public ResponseEntity<AutorResponseDTO> getAutorByid(@PathVariable Long id) {
		return ResponseEntity.ok(autorService.obterAutorPorId(id));
	}

	@PostMapping("adicionar-autor")
	@Operation(summary = "Cadastrar Autor", description = "Insere um novo autor na base de dados e retorna o objeto criado com seu ID gerado.")
	@ApiResponse(responseCode = "201", description = "Autor criado com sucesso")
	@ApiResponse(responseCode = "400", description = "Dados inválidos enviados no corpo da requisição")
	public ResponseEntity<AutorResponseDTO> adicionarAutor(@Valid @RequestBody AutorRequestDTO dto) {
		return ResponseEntity.ok(autorService.salvar(dto));
	}

	@PutMapping("/atualizar-autor/{id}")
	@Operation(summary = "Atualizar Autor", description = "Atualiza dados de um autor")
	@ApiResponse(responseCode = "200", description = "Autor Atualizado")
	public ResponseEntity<AutorResponseDTO> atualizarAutor(@PathVariable Long id,
			@Valid @RequestBody AutorRequestDTO dto) {
		return ResponseEntity.ok(autorService.atualizar(id, dto));
	}

	@DeleteMapping("/deletar-autor/{id}")
	@Operation(summary = "Remover Autor", description = "Remove um autor permanentemente da base de dados")
	@ApiResponse(responseCode = "204", description = "Autor Removido com Sucesso (Sem conteúdo de retorno)")
	public ResponseEntity<Void> deletarAutor(@PathVariable Long id) {
		autorService.deletar(id);
		return ResponseEntity.noContent().build();
	}

}

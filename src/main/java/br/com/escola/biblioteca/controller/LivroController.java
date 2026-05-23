package br.com.escola.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.biblioteca.dto.LivroRequestDTO;
import br.com.escola.biblioteca.dto.LivroResponseDTO;
import br.com.escola.biblioteca.service.LivroService;
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
	public ResponseEntity<List<LivroResponseDTO>> getallLivro() {
		return ResponseEntity.ok(livroService.listarLivros());
	}

	@GetMapping("/{id}")
	public ResponseEntity<LivroResponseDTO> getLivrosByid(@PathVariable Long id) {
		return ResponseEntity.ok(livroService.obterLivroPorId(id));
	}

	@PostMapping("adicionar-livro")
	public ResponseEntity<LivroResponseDTO> adicionarLivro(@Valid @RequestBody LivroRequestDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(livroService.salvar(dto));

	}

	@PutMapping("/atualizar-livro/{id}")
	public ResponseEntity<LivroResponseDTO> atualizarLivro(@PathVariable Long id,
			@Valid @RequestBody LivroRequestDTO dto) {
		return ResponseEntity.ok(livroService.atualizar(id, dto));
	}

	@DeleteMapping("/deletar-livro/{id}")
	public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {
		livroService.deletar(id);
		return ResponseEntity.noContent().build();
	}

}
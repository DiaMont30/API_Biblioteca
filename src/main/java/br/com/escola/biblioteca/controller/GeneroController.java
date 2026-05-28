package br.com.escola.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.biblioteca.dto.GeneroRequestDTO;
import br.com.escola.biblioteca.dto.GeneroResponseDTO;
import br.com.escola.biblioteca.service.GeneroService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/generos")
@Tag(name = "Gêneros", description = "Gerenciamento das categorias de livros")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @GetMapping("/todos-os-generos")
    public ResponseEntity<List<GeneroResponseDTO>> listar() {
        return ResponseEntity.ok(generoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneroResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(generoService.buscarPorId(id));
    }

    @PostMapping("/adicionar-genero")
    public ResponseEntity<GeneroResponseDTO> cadastrar(@Valid @RequestBody GeneroRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(generoService.salvar(dto));
    }

    @PutMapping("/atualizar-genero/{id}")
    public ResponseEntity<GeneroResponseDTO> atualizar(@PathVariable Long id,
            @Valid @RequestBody GeneroRequestDTO dto) {
        return ResponseEntity.ok(generoService.atualizar(id, dto));
    }

    @DeleteMapping("/deletar-genero/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        generoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

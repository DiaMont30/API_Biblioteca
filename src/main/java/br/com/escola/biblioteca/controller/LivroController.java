package br.com.escola.biblioteca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.biblioteca.dto.LivroRequestDTO;
import br.com.escola.biblioteca.dto.LivroResponseDTO;
import br.com.escola.biblioteca.service.AutorService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/livros")
public class LivroController {

@Autowired
private AutorService LivroService;

 
@GetMapping("/todos-os-livros")
public ResponseEntity<List<LivroResponseDTO>>getallLivro(){
    return ResponseEntity.ok(LivroService.listarTodos());
}


@GetMapping("/{id}")
public ResponseEntity<LivroResponseDTO> getLivrosByid(@PathVariable Long id){
    return ResponseEntity.ok(LivroService.buscarPorId(id));
}
 
@PostMapping("adicionar-livro")
public ResponseEntity<LivroRequestDTO> adicionarLivro(@Valid @RequestBody LivroRequestDTO dto) {
return ResponseEntity.status(HttpStatus.CREATED).body(LivroService.salvar(dto));
    
}

@PutMapping("/atualizar-livro/{id}")
public ResponseEntity<LivroRequestDTO> atualizarLivro(@PathVariable Long id, @Valid @RequestBody LivroRequestDTO dto) {
    return ResponseEntity.ok(LivroService.atualizar(id, dto));
}

@DeleteMapping("/deletar-livro/{id}")
public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {
    LivroService.deletar(id);
    return ResponseEntity.noContent().build();
}

   
}
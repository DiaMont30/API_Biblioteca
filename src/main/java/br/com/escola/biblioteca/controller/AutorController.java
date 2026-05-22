package br.com.escola.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.biblioteca.dto.AutorRequestDTO;
import br.com.escola.biblioteca.dto.AutorResponseDTO;
import br.com.escola.biblioteca.service.AutorService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/Autor")
public class AutorController {
    

    @Autowired
    private AutorService autorService;

   @GetMapping("/todos-os-autores")
   public ResponseEntity<List<AutorResponseDTO>>getallAutor(){
    return ResponseEntity.ok(autorService.listarAutores());
   
    }

@GetMapping("/{id}")
public ResponseEntity<AutorResponseDTO> getAutorByid(@PathVariable Long id){
    return ResponseEntity.ok(autorService.obterAutorPorId(id));
}

@PostMapping("adicionar-autor")
public ResponseEntity<AutorResponseDTO> adicionarAutor(@Valid @RequestBody AutorRequestDTO dto) {
    return ResponseEntity.ok(autorService.salvar(dto));
}

@PutMapping("/atualizar-autor/{id}")
public ResponseEntity<AutorResponseDTO> atualizarAutor(@PathVariable Long id,@Valid @RequestBody AutorRequestDTO dto) {
    return ResponseEntity.ok(autorService.atualizar(id, dto));
}

@DeleteMapping("/deletar-autor/{id}")
public ResponseEntity<Void> deletarAutor(@PathVariable Long id) {
    autorService.deletar(id);
    return ResponseEntity.noContent().build();
}

}

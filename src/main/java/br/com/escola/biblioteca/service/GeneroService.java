package br.com.escola.biblioteca.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.escola.biblioteca.dto.GeneroRequestDTO;
import br.com.escola.biblioteca.dto.GeneroResponseDTO;
import br.com.escola.biblioteca.entity.Genero;
import br.com.escola.biblioteca.exception.VerificarExisteException;
import br.com.escola.biblioteca.repository.GeneroRepository;

@Service
public class GeneroService {

	@Autowired
    private GeneroRepository generoRepository;

    public List<GeneroResponseDTO> listarTodos() {
        return generoRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public GeneroResponseDTO buscarPorId(Long id) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new VerificarExisteException("Gênero não encontrado com ID: " + id));
        return mapToResponse(genero);
    }

    public GeneroResponseDTO salvar(GeneroRequestDTO dto) {
        Genero genero = new Genero();
        genero.setNome(dto.nome());
        genero.setSigla(dto.sigla()); // 
        return mapToResponse(generoRepository.save(genero));
    }

    public GeneroResponseDTO atualizar(Long id, GeneroRequestDTO dto) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new VerificarExisteException("Não foi possível atualizar. Gênero não encontrado."));
        
        genero.setNome(dto.nome());
        genero.setSigla(dto.sigla());
        
        return mapToResponse(generoRepository.save(genero));
    }

    public void deletar(Long id) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new VerificarExisteException("Não foi possível deletar. Gênero não encontrado."));
        
        if (genero.getLivros() != null && !genero.getLivros().isEmpty()) {
            throw new VerificarExisteException("Não é possível excluir um gênero que possui livros vinculados.");
        }
        
        generoRepository.deleteById(id);
    }

    private GeneroResponseDTO mapToResponse(Genero genero) {
        return new GeneroResponseDTO(
            genero.getId(),
            genero.getNome(),
            genero.getSigla().getSiglaAbreviada()// 
        );
    }
}

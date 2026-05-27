package br.com.escola.biblioteca.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.escola.biblioteca.dto.AutorRequestDTO;
import br.com.escola.biblioteca.dto.AutorResponseDTO;
import br.com.escola.biblioteca.dto.LivroSimplesDTO;
import br.com.escola.biblioteca.entity.Autor;
import br.com.escola.biblioteca.exception.VerificarExisteException;
import br.com.escola.biblioteca.repository.AutorRepository;

@Service
public class AutorService {
  @Autowired
  private AutorRepository autorRepository;

  public List<AutorResponseDTO> listarAutores() {
    List<Autor> autores = autorRepository.findAll();
    return autores.stream()
        .map(this::mapToResponseDTO)
        .collect(Collectors.toList());
  }

  public AutorResponseDTO obterAutorPorId(Long id) {
    Autor autor = buscarEntidadePorId(id);
    return mapToResponseDTO(autor);
  }

  public AutorResponseDTO salvar(AutorRequestDTO dto) {
    Autor autor = new Autor();
    copiarDadosParaEntidade(autor, dto);

    return mapToResponseDTO(autorRepository.save(autor));
  }

  public AutorResponseDTO atualizar(Long id, AutorRequestDTO dto) {

    Autor autor = buscarEntidadePorId(id);
    copiarDadosParaEntidade(autor, dto);

    return mapToResponseDTO(autorRepository.save(autor));
  }

  public void deletar(Long id) {
	  Autor autor = buscarEntidadePorId(id); 
	  
    if (!autor.getLivros().isEmpty()) {
        throw new VerificarExisteException("Não é possível deletar o autor pois ele possui livros cadastrados.");
    }
    autorRepository.deleteById(id);
  }


  private Autor buscarEntidadePorId(Long id) {
    return autorRepository.findById(id)
        .orElseThrow(() -> new VerificarExisteException("Autor não encontrado com o id: " + id));
  }

  private void copiarDadosParaEntidade(Autor autor, AutorRequestDTO dto) {
    autor.setNome(dto.nome());
    autor.setNacionalidade(dto.nacionalidade());
    autor.setDataNascimento(dto.dataNascimento());
  }

  private AutorResponseDTO mapToResponseDTO(Autor autor) {
      List<LivroSimplesDTO> livrosDTO = null;
      if (autor.getLivros() != null) {
          livrosDTO = autor.getLivros().stream()
              .map(livro -> new LivroSimplesDTO(
                  livro.getId(),
                  livro.getTitulo(),
                  livro.getIsbn(),
                  livro.getAnoPublicacao()
              ))
              .collect(Collectors.toList());
      }
      return new AutorResponseDTO(
          autor.getId(),
          autor.getNome(),
          autor.getNacionalidade(),
          autor.getDataNascimento(),
          livrosDTO
      );
  }
}

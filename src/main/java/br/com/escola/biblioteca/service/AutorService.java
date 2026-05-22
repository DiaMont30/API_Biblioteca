package br.com.escola.biblioteca.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.escola.biblioteca.dto.AutorRequestDTO;
import br.com.escola.biblioteca.dto.AutorResponseDTO;
import br.com.escola.biblioteca.entity.Autor;
import br.com.escola.biblioteca.repository.AutorRepository;

@Service
public class AutorService {
  @Autowired
  private AutorRepository autorRepository;

  public List<AutorResponseDTO> listarAutores() {
    List<Autor> autores = autorRepository.findAll();
    return autores.stream()
        .map(autor -> new AutorResponseDTO(autor.getNome(), autor.getNacionalidade(), autor.getDataNascimento()))
        .collect(Collectors.toList());
  }

  public AutorResponseDTO obterAutorPorId(Long id) {
    Autor autor = autorRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
    return new AutorResponseDTO(autor.getNome(), autor.getNacionalidade(), autor.getDataNascimento());
  }

  public Autor salvar(AutorRequestDTO dto) {
    Autor autor = new Autor();
    autor.setNome(dto.nome());
    autor.setNacionalidade(dto.nacionalidade());
    autor.SetDataNascimento(dto.dataNascimento());

    return autorRepository.save(autor);
  }

  public Autor atualizar(Long id, AutorRequestDTO dto) {
    Autor autor = obterAutorPorId(id);
    autor.setNome(dto.nome());
    autor.setNacionalidade(dto.nacionalidade());
    autor.SetDataNascimento(dto.dataNascimento());

    return autorRepository.save(autor);
  }

  public void deletar(Long id) {
    if (!autorRepository.existsById(id)) {
      throw new RuntimeException("Autor não encontrado com id: " + id);
    }
    autorRepository.deleteById(id);
  }
}

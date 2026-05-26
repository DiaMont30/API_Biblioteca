package br.com.escola.biblioteca.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.escola.biblioteca.dto.LivroRequestDTO;
import br.com.escola.biblioteca.dto.LivroResponseDTO;
import br.com.escola.biblioteca.entity.Autor;
import br.com.escola.biblioteca.entity.Livro;
import br.com.escola.biblioteca.exception.VerificarExisteException;
import br.com.escola.biblioteca.repository.AutorRepository;
import br.com.escola.biblioteca.repository.LivroRepository;

@Service
public class LivroService {

        @Autowired
        private LivroRepository livroRepository;

        @Autowired
        private AutorRepository autorRepository;

        public List<LivroResponseDTO> listarLivros() {
                List<Livro> livros = livroRepository.findAll();
                return livros.stream()
                                .map(this::mapToResponseDTO)
                                .collect(Collectors.toList());
        }

        public LivroResponseDTO obterLivroPorId(Long id) {
                Livro livro = buscarEntidadePorId(id);
                return new LivroResponseDTO(id, livro.getTitulo(), livro.getIsbn(), livro.getAnoPublicacao(),
                                livro.getGenero(),
                                livro.getAutor().getId(), livro.getAutor().getNome());
        }

        public LivroResponseDTO salvar(LivroRequestDTO dto) {
                Autor autor = autorRepository.findById(dto.autorId())
                                .orElseThrow(() -> new RuntimeException(
                                                "Autor não encontrado com id: " + dto.autorId()));

                Livro livro = new Livro();
                importeDadosParaEntidade(livro, dto);
                livro.setAutor(autor);
                return mapToResponseDTO(livroRepository.save(livro));
        }

        public LivroResponseDTO atualizar(Long id, LivroRequestDTO dto) {
                Livro livro = livroRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException(
                                                "Livro não encontrado com id: " + id));

                Autor autor = autorRepository.findById(dto.autorId())
                                .orElseThrow(() -> new VerificarExisteException(
                                                "Não foi possível atualizar o livro. Autor não foi encontrado com o id: "
                                                                + dto.autorId()));

                importeDadosParaEntidade(livro, dto);
                livro.setAutor(autor);
                return mapToResponseDTO(livroRepository.save(livro));

        }

        public void deletar(Long id) {
                if (!livroRepository.existsById(id)) {
                        throw new VerificarExisteException(
                                        "Não foi possível deletar o livro. Livro não foi encontrado com o id: " + id);
                }

                livroRepository.deleteById(id);
        }

        private void importeDadosParaEntidade(Livro livro, LivroRequestDTO dto) {
                livro.setTitulo(dto.titulo());
                livro.setIsbn(dto.isbn());
                livro.setAnoPublicacao(dto.anoPublicacao());
                livro.setGenero(dto.genero());
        }

        private Livro buscarEntidadePorId(Long id) {
                return livroRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Livro não encontrado com o id: " + id));
        }

        private LivroResponseDTO mapToResponseDTO(Livro livro) {
                return new LivroResponseDTO(
                                livro.getId(),
                                livro.getTitulo(),
                                livro.getIsbn(),
                                livro.getAnoPublicacao(),
                                livro.getGenero(),
                                livro.getAutor().getId(),
                                livro.getAutor().getNome());
        }
}
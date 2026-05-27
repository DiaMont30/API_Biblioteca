package br.com.escola.biblioteca.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.escola.biblioteca.dto.LivroRequestDTO;
import br.com.escola.biblioteca.dto.LivroResponseDTO;
import br.com.escola.biblioteca.entity.Autor;
import br.com.escola.biblioteca.entity.Editora;
import br.com.escola.biblioteca.entity.Genero;
import br.com.escola.biblioteca.entity.Livro;
import br.com.escola.biblioteca.exception.VerificarExisteException;
import br.com.escola.biblioteca.repository.AutorRepository;
import br.com.escola.biblioteca.repository.EditoraRepository;
import br.com.escola.biblioteca.repository.GeneroRepository;
import br.com.escola.biblioteca.repository.LivroRepository;

@Service
public class LivroService {

        @Autowired
        private LivroRepository livroRepository;
        @Autowired
        private AutorRepository autorRepository;
        @Autowired
        private GeneroRepository generoRepository;
        @Autowired
        private EditoraRepository editoraRepository;

        public List<LivroResponseDTO> listarLivros() {
                List<Livro> livros = livroRepository.findAll();
                return livros.stream()
                                .map(this::mapToResponseDTO)
                                .collect(Collectors.toList());
        }

        public LivroResponseDTO obterLivroPorId(Long id) {
                Livro livro = buscarEntidadePorId(id);
                return mapToResponseDTO(livro);
        }

        public LivroResponseDTO salvar(LivroRequestDTO dto) {
                // Busca as 3 entidades obrigatórias
                Autor autor = autorRepository.findById(dto.autorId())
                                .orElseThrow(() -> new VerificarExisteException(
                                                "Autor não encontrado com id: " + dto.autorId()));

                Genero genero = generoRepository.findById(dto.generoId())
                                .orElseThrow(() -> new VerificarExisteException(
                                                "Gênero não encontrado com id: " + dto.generoId()));

                Editora editora = editoraRepository.findById(dto.editoraId())
                                .orElseThrow(() -> new VerificarExisteException(
                                                "Editora não encontrada com id: " + dto.editoraId()));

                Livro livro = new Livro();
                importeDadosParaEntidade(livro, dto);

                // Seta os objetos reais na entidade
                livro.setAutor(autor);
                livro.setGenero(genero);
                livro.setEditora(editora);

                return mapToResponseDTO(livroRepository.save(livro));
        }

        public LivroResponseDTO atualizar(Long id, LivroRequestDTO dto) {
                Livro livro = buscarEntidadePorId(id);

                Autor autor = autorRepository.findById(dto.autorId())
                                .orElseThrow(() -> new VerificarExisteException(
                                                "Autor não encontrado com id: " + dto.autorId()));

                Genero genero = generoRepository.findById(dto.generoId())
                                .orElseThrow(() -> new VerificarExisteException(
                                                "Gênero não encontrado com id: " + dto.generoId()));

                Editora editora = editoraRepository.findById(dto.editoraId())
                                .orElseThrow(() -> new VerificarExisteException(
                                                "Editora não encontrada com id: " + dto.editoraId()));

                importeDadosParaEntidade(livro, dto);
                livro.setAutor(autor);
                livro.setGenero(genero);
                livro.setEditora(editora);

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
                                livro.getGenero().getId(),
                                livro.getGenero().getNome(),
                                livro.getAutor().getId(),
                                livro.getAutor().getNome(),
                                livro.getEditora().getId(),
                                livro.getEditora().getNome());
        }
}
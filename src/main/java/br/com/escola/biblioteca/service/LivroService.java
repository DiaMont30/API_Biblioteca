package br.com.escola.biblioteca.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.escola.biblioteca.config.MailConfig;
import br.com.escola.biblioteca.dto.LivroRequestDTO;
import br.com.escola.biblioteca.dto.LivroResponseDTO;
import br.com.escola.biblioteca.entity.Autor;
import br.com.escola.biblioteca.entity.Editora;
import br.com.escola.biblioteca.entity.Genero;
import br.com.escola.biblioteca.entity.Livro;
import br.com.escola.biblioteca.entity.Usuario;
import br.com.escola.biblioteca.exception.EmailException;
import br.com.escola.biblioteca.exception.VerificarExisteException;
import br.com.escola.biblioteca.repository.AutorRepository;
import br.com.escola.biblioteca.repository.EditoraRepository;
import br.com.escola.biblioteca.repository.GeneroRepository;
import br.com.escola.biblioteca.repository.LivroRepository;
import br.com.escola.biblioteca.repository.UsuarioRepository;

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

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MailConfig mailConfig;

    public List<LivroResponseDTO> listarLivros() {
        List<Livro> livros = livroRepository.findAll();
        return livros.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public LivroResponseDTO obterLivroPorId(Long id) {
        Livro livro = buscarLivroPorId(id);
        return mapToResponseDTO(livro);
    }

    public LivroResponseDTO salvar(LivroRequestDTO dto) {
        if (livroRepository.existsByIsbn(dto.isbn())) {
            throw new VerificarExisteException("Já existe um livro cadastrado com o ISBN: " + dto.isbn());
        }

        Autor autor = buscarAutorPorId(dto.autorId());
        Genero genero = buscarGeneroPorId(dto.generoId());
        Editora editora = buscarEditoraPorId(dto.editoraId());

        Livro livro = new Livro();
        importeDadosParaEntidade(livro, dto);

        livro.setAutor(autor);
        livro.setGenero(genero);
        livro.setEditora(editora);

        livroRepository.save(livro);
        enviarEmail(livro.getTitulo(), "Cadastrado");

        return mapToResponseDTO(livro);
    }

    public LivroResponseDTO atualizar(Long id, LivroRequestDTO dto) {
        Livro livro = buscarLivroPorId(id);
        Autor autor = buscarAutorPorId(dto.autorId());
        Genero genero = buscarGeneroPorId(dto.generoId());
        Editora editora = buscarEditoraPorId(dto.editoraId());

        importeDadosParaEntidade(livro, dto);
        livro.setAutor(autor);
        livro.setGenero(genero);
        livro.setEditora(editora);

        livroRepository.save(livro);
        enviarEmail(livro.getTitulo(), "Atualizado");

        return mapToResponseDTO(livro);
    }

    public void deletar(Long id) {
        Livro livro = buscarLivroPorId(id);
        String titulo = livro.getTitulo();
        livroRepository.deleteById(id);
        enviarEmail(titulo, "Excluído");
    }

    private void importeDadosParaEntidade(Livro livro, LivroRequestDTO dto) {
        livro.setTitulo(dto.titulo());
        livro.setIsbn(dto.isbn());
        livro.setAnoPublicacao(dto.anoPublicacao());
    }

    private Livro buscarLivroPorId(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new VerificarExisteException("Livro não encontrado com o id: " + id));
    }

    private Autor buscarAutorPorId(Long id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new VerificarExisteException("Autor não encontrado com o id: " + id));
    }

    private Genero buscarGeneroPorId(Long id) {
        return generoRepository.findById(id)
                .orElseThrow(() -> new VerificarExisteException("Gênero não encontrado com o id: " + id));
    }

    private Editora buscarEditoraPorId(Long id) {
        return editoraRepository.findById(id)
                .orElseThrow(() -> new VerificarExisteException("Editora não encontrada com o id: " + id));
    }

    private LivroResponseDTO mapToResponseDTO(Livro livro) {
        return new LivroResponseDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getIsbn(),
                livro.getAnoPublicacao(),
                livro.getGenero().getId(),
                livro.getGenero().getSigla(),
                livro.getAutor().getId(),
                livro.getAutor().getNome(),
                livro.getEditora().getId(),
                livro.getEditora().getNome());
    }

    private Usuario getUsuarioLogado() {
        String emailLogado = org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication().getName();

        Object usuarioBanco = usuarioRepository.findByEmail(emailLogado);

        if (usuarioBanco == null) {
            throw new VerificarExisteException("Usuário logado não encontrado no banco.");
        }
        return (Usuario) usuarioBanco;
    }

    private void enviarEmail(String tituloLivro, String acao) {
        Usuario usuario = getUsuarioLogado();
        try {
            String assunto = "Aviso de Atualização: " + tituloLivro;

            mailConfig.sendEmail(
                    usuario.getEmail(),
                    assunto,
                    usuario.getNome(),
                    tituloLivro,
                    acao);

        } catch (Exception e) {
            throw new EmailException("Livro " + acao + ", mas o e-mail falhou: " + e.getMessage());
        }
    }
}
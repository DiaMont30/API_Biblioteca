package br.com.escola.biblioteca.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.escola.biblioteca.dto.UsuarioRequestDTO;
import br.com.escola.biblioteca.dto.UsuarioResponseDTO;
import br.com.escola.biblioteca.entity.Usuario;
import br.com.escola.biblioteca.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UsuarioResponseDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO listarUsuarioPorId(Long id) {
        Usuario usuario = buscarUsuarioPorId(id);

        return new UsuarioResponseDTO(id, usuario.getNome(), usuario.getEmail(), usuario.getRole());

    }

    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto) {
        Usuario usuario = buscarUsuarioPorId(id);
        copiarDadosParaEntidade(usuario, dto);

        return mapToResponseDTO(usuarioRepository.save(usuario));
    }

    // public UsuarioResponseDTO salvar(UsuarioRequestDTO dto){}

    public void deletar(long id) {
        buscarUsuarioPorId(id);

        usuarioRepository.deleteById(id);
    }

    private Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o id: " + id));
    }

    private void copiarDadosParaEntidade(Usuario usuario, UsuarioRequestDTO dto) {
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setRole(dto.role());
    }

    private UsuarioResponseDTO mapToResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole());
    }
}

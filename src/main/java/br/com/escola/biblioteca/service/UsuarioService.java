package br.com.escola.biblioteca.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.escola.biblioteca.dto.UsuarioAtualizarDTO;
import br.com.escola.biblioteca.dto.UsuarioResponseDTO;
import br.com.escola.biblioteca.entity.Usuario;
import br.com.escola.biblioteca.exception.VerificarExisteException;
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

        return mapToResponseDTO(usuario);

    }

    public UsuarioResponseDTO atualizar(Long id, UsuarioAtualizarDTO dto) {
        Usuario usuario = buscarUsuarioPorId(id);
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());

        return mapToResponseDTO(usuarioRepository.save(usuario));
    }

    public void alterarSenha(Long id, String senhaAntiga, String novaSenha) {
        Usuario usuario = buscarUsuarioPorId(id);

        if (!passwordEncoder.matches(senhaAntiga, usuario.getSenha())) {
            throw new VerificarExisteException("A senha antiga está incorreta.");
        }

        usuario.setSenha(passwordEncoder.encode(novaSenha));
        usuarioRepository.save(usuario);
    }

    public void deletar(long id) {
        buscarUsuarioPorId(id);

        usuarioRepository.deleteById(id);
    }

    private Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new VerificarExisteException(
                        "Usuário não encontrado com o id: " + id));
    }

    private UsuarioResponseDTO mapToResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole());
    }
}

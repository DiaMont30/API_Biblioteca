package br.com.escola.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.biblioteca.dto.AutenticacaoDTO;
import br.com.escola.biblioteca.dto.TokenResponseDTO;
import br.com.escola.biblioteca.dto.UsuarioRequestDTO;
import br.com.escola.biblioteca.dto.UsuarioResponseDTO;
import br.com.escola.biblioteca.entity.Usuario;
import br.com.escola.biblioteca.repository.UsuarioRepository;
import br.com.escola.biblioteca.service.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Rotas de autenticação da aplicação")
public class AutenticacaoController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UsuarioRepository repository;

  @Autowired
  private TokenService tokenService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostMapping("/autenticar")
  public ResponseEntity<TokenResponseDTO> autenticar(@RequestBody @Valid AutenticacaoDTO dto) {
    var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
    var auth = this.authenticationManager.authenticate(usernamePassword);
    var token = tokenService.gerarToken((Usuario) auth.getPrincipal());
    return ResponseEntity.ok(new TokenResponseDTO(token));
  }

  @PostMapping("/registrar")

  public ResponseEntity<UsuarioResponseDTO> registrar(@RequestBody @Valid UsuarioRequestDTO dto) {
    if (this.repository.findByEmail(dto.email()) != null) {
      return ResponseEntity.badRequest().build();
    }

    String senhaCriptografada = passwordEncoder.encode(dto.senha());

    Usuario novoUsuario = new Usuario(dto.nome(), dto.email(), senhaCriptografada, dto.role());

    Usuario salvo = this.repository.save(novoUsuario);

    return ResponseEntity.status(HttpStatus.CREATED)
        .contentType(MediaType.APPLICATION_JSON)
        .body(new UsuarioResponseDTO(
            salvo.getId(),
            salvo.getNome(),
            salvo.getEmail(),
            salvo.getRole()));
  }

}
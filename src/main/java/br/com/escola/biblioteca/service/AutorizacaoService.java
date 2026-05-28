package br.com.escola.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.escola.biblioteca.repository.UsuarioRepository;

@Service
public class AutorizacaoService implements UserDetailsService {

  @Autowired
  private UsuarioRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserDetails usuario = repository.findByEmail(username);

    if (usuario == null) {
      throw new UsernameNotFoundException("Credenciais inválidas.");
    }

    return usuario;
  }
}
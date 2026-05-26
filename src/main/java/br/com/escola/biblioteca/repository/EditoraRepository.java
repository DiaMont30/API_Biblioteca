package br.com.escola.biblioteca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.escola.biblioteca.entity.Editora;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long> {
    boolean existsByCnpj(String cnpj);
    Optional<Editora> findByCnpj(String cnpj);
}

package br.com.escola.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.escola.biblioteca.entity.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

}
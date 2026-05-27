package br.com.escola.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.escola.biblioteca.entity.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {
}

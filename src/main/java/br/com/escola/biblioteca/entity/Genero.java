package br.com.escola.biblioteca.entity;

import java.util.List;

import br.com.escola.biblioteca.enums.SiglaGeneroEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "genero")
public class Genero {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nome;

    @Enumerated(EnumType.STRING) // Salva o nome do enum (ex: "ROMANCE")
    @Column(nullable = false, length = 10)
    private SiglaGeneroEnum sigla;

    @OneToMany(mappedBy = "genero")
    private List<Livro> livros;

	public Genero() {
		super();
	}

	public Genero(Long id, String nome, SiglaGeneroEnum sigla, List<Livro> livros) {
		super();
		this.id = id;
		this.nome = nome;
		this.sigla = sigla;
		this.livros = livros;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public SiglaGeneroEnum getSigla() {
		return sigla;
	}

	public void setSigla(SiglaGeneroEnum sigla) {
		this.sigla = sigla;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
	
}

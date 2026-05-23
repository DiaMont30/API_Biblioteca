package br.com.escola.biblioteca.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="autor")
public class Autor {

	@Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Schema(description="Identificador unico do autor", example="1")
	private Long id;
	
	@Column (name = "nome", nullable = false, length = 60)
	@Schema(description = "Nome do autor", example = "João da Silva", requiredMode = Schema.RequiredMode.REQUIRED)
	private String nome;
	
	@Column (name = "nacionalidade", length = 20)
	@Schema(description="Nacionalidado do autor", example="Brasileiro")
	private String nacionalidade;
	
	@Column(name = "data_nascimento")
	@Schema(description="Data de nascimento do autor", example="2000-04-04")
	private LocalDate dataNascimento;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "autor", fetch = FetchType.LAZY)
	@Schema(description="Lista de livros do autor")
	private List<Livro> livros;

	public Autor() {
	}

	public Autor(Long id, String nome, String nacionalidade, LocalDate dataNascimento) {
		this.id = id;
		this.nome = nome;
		this.nacionalidade = nacionalidade;
		this.dataNascimento = dataNascimento;
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

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
	
	
}

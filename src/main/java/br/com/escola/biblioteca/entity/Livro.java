package br.com.escola.biblioteca.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="livro")
public class Livro {

	@Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Schema(description="Identificador unico do livro", example="1")
	private Long id;
	
	@Column (name = "titulo", nullable = false, length = 30)
	@Schema(description = "Titulo do livro", example = "O Pequeno Principe", requiredMode = Schema.RequiredMode.REQUIRED)
	private String titulo;
	
	@Column(name = "isbn", unique = true, length = 17)
	@Schema(description = "ISBN do livro no formato 978-3-16-148410-0", example = "978-85-333-0227-3")
	private String isbn;
	
	@Column(name = "ano_publicacao")
	@Schema(description = "Ano em que o livro foi publicado", example = "2024")
	private Integer anoPublicacao;
	
	@Column(name = "genero", length = 30)
	@Schema(description = "Gênero literário do livro", example = "Ficção Científica")
	private String genero;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="id_autor")
	private Autor autor;

	public Livro() {

	}

	public Livro(Long id, String titulo, String isbn, Integer anoPublicacao, String genero) {
		this.id = id;
		this.titulo = titulo;
		this.isbn = isbn;
		this.anoPublicacao = anoPublicacao;
		this.genero = genero;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setAnoPublicacao(Integer anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
}

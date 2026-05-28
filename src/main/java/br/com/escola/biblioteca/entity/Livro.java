package br.com.escola.biblioteca.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "livro")
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "titulo", nullable = false, length = 60)
	private String titulo;

	@Column(name = "isbn", unique = true, length = 17)
	private String isbn;

	@Column(name = "ano_publicacao")
	private Integer anoPublicacao;

	@ManyToOne
	@JoinColumn(name = "id_genero", nullable = false)
	private Genero genero;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_autor", nullable = false)
	private Autor autor;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "editora_id", nullable = false)
	private Editora editora;

	public Livro() {

	}

	public Livro(Long id, String titulo, String isbn, Integer anoPublicacao, Genero genero, Autor autor, Editora editora) {
		this.id = id;
		this.titulo = titulo;
		this.isbn = isbn;
		this.anoPublicacao = anoPublicacao;
		this.genero = genero;
		this.autor = autor;
		this.editora = editora;
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

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	public Editora getEditora() { 
		return editora;
	}

	public void setEditora(Editora editora) { 
		this.editora = editora;
	}

}

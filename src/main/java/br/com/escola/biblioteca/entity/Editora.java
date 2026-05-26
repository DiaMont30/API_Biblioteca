package br.com.escola.biblioteca.entity;

import br.com.escola.biblioteca.enums.EstadoBrasileiro;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "editora")
public class Editora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "cnpj", nullable = false, unique = true, length = 18)
    private String cnpj;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 2)
    private EstadoBrasileiro estado;

    @JsonManagedReference
    @OneToMany(mappedBy = "editora", fetch = FetchType.LAZY)
    private List<Livro> livros = new ArrayList<>();

    public Editora() {
    }

    public Editora(Long id, String nome, String cnpj, EstadoBrasileiro estado) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.estado = estado;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public EstadoBrasileiro getEstado() {
        return estado;
    }

    public void setEstado(EstadoBrasileiro estado) {
        this.estado = estado;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
}
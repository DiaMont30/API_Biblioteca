# Grupo 6

> **Serratec Residência de Software · Sala 34 · Trabalho avaliativo da disciplina de Desenvolvimento de API Restful**

---

## Bem-vindos, Grupo 6!

Este repositório centraliza e versiona a API desenvolvida pelo **Grupo 6** como parte da disciplina de **Desenvolvimento de API Restful** do programa **Serratec Residência de Software**.

O projeto consiste em uma API de cadastro de autores e livros, representando uma biblioteca simples.

---

## Integrantes

- DIANA MONTEIRO
- GABRIEL AGUIAR
- LEONAM NOGUEIRA MACHADO
- THIAGO ROCHA
- VANESSA XAVIER

---

## O Trabalho

A atividade consiste no desenvolvimento de uma API de cadastro de autores e livros, representando uma biblioteca simples, totalizando **10 pontos**.

## Estrutura do Repositório

```
API_Biblioteca/src/main/java/br/com/escola/biblioteca
│
├── annotation
|   └── ApiRespostasPadrao.java
|   └── SwaggerExemplos.java
│
├── config
|   └── SwaggerConfig.java
|   └── SecurityFilter.java
|   └── SecurityConfig.java
|
├── controller
│   ├── AutenticacaoController.java
│   ├── AutorController.java
│   ├── EditoraController.java
│   ├── GeneroController.java
│   ├── LivroController.java
│   └── UsuarioController.java
│
├── service
│   ├── AutorizacaoService.java
│   ├── AutorService.java
│   ├── EditoraService.java
│   ├── EmailService.java
│   ├── GeneroService.java
│   ├── TokenService.java
│   ├── UsuarioService.java
│   └── LivroService.java
│
├── repository
│   ├── AutorRepository.java
│   ├── EditoraRepository.java
│   ├── GeneroRepository.java
│   ├── UsuarioRepository.java
│   └── LivroRepository.java
│
├── dto
│   ├── AlterarSenhaDTO.java
│   ├── AutenticacaoDTO.java
│   ├── AutorRequestDTO.java
│   ├── AutorResponseDTO.java
│   ├── EditoraResponseDTO.java
│   ├── EditoraRequestDTO.java
│   ├── GeneroRequestDTO.java
│   ├── GeneroResponseDTO.java
│   ├── LivroRequestDTO.java
│   ├── LivroResponseDTO.java
│   ├── LivroSimplesDTO.java
│   ├── TokenResponseDTO.java
│   ├── UsuarioAtualizarDTO.java
│   ├── UsuarioRequestDTO.java
│   └── UsuarioResponseDTO.java
│
├── entity
│   ├── Autor.java
│   ├── Editora.java
│   ├── Genero.java
│   ├── Livro.java
│   └── Usuario.java
│
├── enums
│   ├── EstadoBrasileiro.java
│   ├── SiglaGenero.java
│   └── UsuarioRole.java
│
└── exception
  ├── ControllerExceptionHandler.java
  ├── ErroResposta.java
  ├── EmailException.java
  └── VerificarExisteException.java
```

---

## URL para clonar o repositório

```bash
git clone https://github.com/DiaMont30/API_Biblioteca.git
```

## URL para acessar o Swagger

```
http://localhost:8080/swagger-ui/index.html
```

## Ferramentas

- [PostgreSQL](https://www.postgresql.org/) — SGBD utilizado no projeto
- [DBeaver](https://dbeaver.io/) — Interface gráfica para gerenciamento do banco
- [Swagger](https://swagger.io/) — Ferramenta de criação da Documentação da API
- [JWT](https://www.jwt.io/) — Ferramenta de implementação do JWT (JSON Web Token), token digital utilizado para autenticação e autorização em APIs e aplicações web.

---

<p align="center">
  <strong>Grupo 6 — Serratec Residência · Sala 34</strong>
</p>

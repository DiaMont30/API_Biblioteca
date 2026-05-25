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
├── config
|   └── SwaggerConfig.java
|
├── controller
│   ├── AutorController.java
│   └── LivroController.java
│
├── service
│   ├── AutorService.java
│   └── LivroService.java
│
├── repository
│   ├── AutorRepository.java
│   └── LivroRepository.java
│
├── dto
│   ├── AutorRequestDTO.java
│   ├── AutorResponseDTO.java
│   ├── LivroRequestDTO.java
│   └── LivroResponseDTO.java
│
├── entity
│   ├── Autor.java
│   └── Livro.java
│
└── exception
  ├── ControllerExceptionHandler.java
  ├── ErroResposta.java
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

---

<p align="center">
  <strong>Grupo 6 — Serratec Residência · Sala 34</strong>
</p>

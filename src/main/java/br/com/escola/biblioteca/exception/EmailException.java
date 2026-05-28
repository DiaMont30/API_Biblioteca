package br.com.escola.biblioteca.exception;

public class EmailException extends RuntimeException {
    public EmailException(String message) {
        super(message);
    }
}
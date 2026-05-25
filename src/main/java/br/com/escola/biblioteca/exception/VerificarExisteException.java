package br.com.escola.biblioteca.exception;

public class VerificarExisteException extends RuntimeException{

	public VerificarExisteException(String mensagem) {
        super(mensagem);
    }
}

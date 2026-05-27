package br.com.escola.biblioteca.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		List<String> erros = new ArrayList<>();

		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			erros.add(error.getField() + ": " + error.getDefaultMessage());
		}

		ErroResposta erroResposta = new ErroResposta(
				status.value(),
				"Existem Campos Inválidos, Confira o preenchimento",
				LocalDateTime.now(),
				erros);

		return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
	}

	@ExceptionHandler(VerificarExisteException.class)
	public ResponseEntity<Object> handleAutor(VerificarExisteException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;

		ErroResposta erroResposta = new ErroResposta(
				status.value(),
				"Erro na validação de dados",
				LocalDateTime.now(),
				List.of(ex.getMessage()));

		return handleExceptionInternal(ex, erroResposta, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<Object> handleBadCredentials(BadCredentialsException ex, WebRequest request) {
		HttpStatus status = HttpStatus.FORBIDDEN;

		ErroResposta erroResposta = new ErroResposta(
				status.value(),
				"Acesso Negado",
				LocalDateTime.now(),
				List.of("E-mail ou senha incorretos. Tente novamente."));

		return handleExceptionInternal(ex, erroResposta, new HttpHeaders(), status, request);
	}
}
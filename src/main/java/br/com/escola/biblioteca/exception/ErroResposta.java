package br.com.escola.biblioteca.exception;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErroResposta {

	private Integer status; 
	 private String titulo;
	 @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	 private LocalDateTime dataHora;
	 private List<String> erros;
	 
	 public ErroResposta(Integer status, String titulo, LocalDateTime dataHora, List<String> erros) { 
		 this.status = status;
		 this.titulo = titulo; 
		 this.dataHora = dataHora;
		 this.erros = erros;
	 }

	 public Integer getStatus() {
		 return status;
	 }

	 public void setStatus(Integer status) {
		 this.status = status;
	 }

	 public String getTitulo() {
		 return titulo;
	 }

	 public void setTitulo(String titulo) {
		 this.titulo = titulo;
	 }

	 public LocalDateTime getDataHora() {
		 return dataHora;
	 }

	 public void setDataHora(LocalDateTime dataHora) {
		 this.dataHora = dataHora;
	 }

	 public List<String> getErros() {
		 return erros;
	 }

	 public void setErros(List<String> erros) {
		 this.erros = erros;
	 }
}

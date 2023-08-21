package br.com.pos.venda.contrato.aditamento.exception;

import lombok.Data;

@Data
public class ExceptionDetails {
	private String message;
	private int status;
}

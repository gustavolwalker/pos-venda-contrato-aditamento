package br.com.pos.venda.contrato.aditamento.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BusinessException extends ResponseEntityExceptionHandler {

	@ExceptionHandler(AditamentoException.class)
	public ResponseEntity<?> paymentException(AditamentoException paymentsException) {
		var details = new ExceptionDetails();
		details.setMessage(paymentsException.getMessage());
		details.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
		return new ResponseEntity<>(details, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName;
			fieldName = ((FieldError) error).getField();
			fieldName = fieldName.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

}

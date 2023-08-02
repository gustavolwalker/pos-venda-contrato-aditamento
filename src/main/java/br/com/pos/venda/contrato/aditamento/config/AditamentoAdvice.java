package br.com.pos.venda.contrato.aditamento.config;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import br.com.pos.venda.contrato.aditamento.model.AditamentoAbstractModel;
import br.com.pos.venda.contrato.aditamento.service.ContratoService;

@RestControllerAdvice
public class AditamentoAdvice implements ResponseBodyAdvice<AditamentoAbstractModel> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		String className = returnType.getContainingClass().toString();
		if (className.contains("AditamentoController")) {
			return true;
		}
		return false;
	}
	
	@Override
	public AditamentoAbstractModel beforeBodyWrite(AditamentoAbstractModel body, MethodParameter returnType,
			MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
			ServerHttpRequest request, ServerHttpResponse response) {

		ContratoService.ajustaResponseUltimoDigitoContrato(body.getContrato());

		return body;
	}

}

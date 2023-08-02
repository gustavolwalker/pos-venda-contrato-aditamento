package br.com.pos.venda.contrato.aditamento.service.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.pos.venda.contrato.aditamento.model.request.CalculoJurosRequestModel;
import br.com.pos.venda.contrato.aditamento.model.response.CalculoJurosResponseModel;

@Service
@FeignClient(url = "${aditamento.service.request.calculo.juros.uri}", name = "calculo-juros-api")
public interface CalculoJurosRequestService {

	@PostMapping(consumes = "application/json", produces = "application/json")
	CalculoJurosResponseModel calculaJuros(@RequestBody CalculoJurosRequestModel request);

}

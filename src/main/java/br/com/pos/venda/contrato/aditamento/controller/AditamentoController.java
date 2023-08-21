package br.com.pos.venda.contrato.aditamento.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.pos.venda.contrato.aditamento.exception.AditamentoException;
import br.com.pos.venda.contrato.aditamento.model.request.AlteraDataPagamentoRequestModel;
import br.com.pos.venda.contrato.aditamento.model.request.AlteraQuantidadeParcelasRequestModel;
import br.com.pos.venda.contrato.aditamento.model.response.AlteraDataPagamentoResponseModel;
import br.com.pos.venda.contrato.aditamento.model.response.AlteraQuantidadeParcelasResponseModel;
import br.com.pos.venda.contrato.aditamento.service.AditamentoService;

@RestController()
@RequestMapping("aditamento")
@Validated
public class AditamentoController {

	@Autowired
	private AditamentoService aditamentoService;

	@PostMapping(value = "/altera-quantidade-parcelas")
	@ResponseStatus(HttpStatus.OK)
	public AlteraQuantidadeParcelasResponseModel alteraQuantidadeParcelas(
			@Valid @RequestBody AlteraQuantidadeParcelasRequestModel payment) throws AditamentoException {
		return aditamentoService.alteraQuantidadeParcelas(payment);
	}

	@PostMapping(value = "/altera-dia-pagamento")
	@ResponseStatus(HttpStatus.OK)
	public AlteraDataPagamentoResponseModel alteraDiaPagamento(
			@Valid @RequestBody AlteraDataPagamentoRequestModel payment) throws AditamentoException {
		return aditamentoService.alteraDataPagamento(payment);
	}

}

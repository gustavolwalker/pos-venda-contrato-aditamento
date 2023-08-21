package br.com.pos.venda.contrato.aditamento.service;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pos.venda.contrato.aditamento.constants.TipoCalculo;
import br.com.pos.venda.contrato.aditamento.exception.AditamentoException;
import br.com.pos.venda.contrato.aditamento.model.ContratoModel;
import br.com.pos.venda.contrato.aditamento.model.MovimentoModel;
import br.com.pos.venda.contrato.aditamento.model.request.AlteraDataPagamentoRequestModel;
import br.com.pos.venda.contrato.aditamento.model.request.AlteraQuantidadeParcelasRequestModel;
import br.com.pos.venda.contrato.aditamento.model.response.AlteraDataPagamentoResponseModel;
import br.com.pos.venda.contrato.aditamento.model.response.AlteraQuantidadeParcelasResponseModel;
import br.com.pos.venda.contrato.aditamento.model.response.CalculoJurosResponseModel;

@Service
public class AditamentoService {

	@Autowired
	private CalculoJurosService calculoJurosService;

	public AlteraQuantidadeParcelasResponseModel alteraQuantidadeParcelas(AlteraQuantidadeParcelasRequestModel request)
			throws AditamentoException {
		try {
			ContratoModel contrato = request.getContrato();

			MovimentoModel contratacaoMovimento = request.getFinanceiro().stream()
					.filter(mov -> mov.getTipoCalculo() == TipoCalculo.CONTRATACAO).findFirst().orElseThrow();

			MovimentoModel ultimoMovimento = request.getFinanceiro().get(request.getFinanceiro().size() - 1);

			int quantidadeParcelas = request.getAditamento().getNovaQuantidadeParcelas();

			ContratoService.validaQuantidadeParcelas(ultimoMovimento.getQuantidadeParcelas(), quantidadeParcelas);
			ContratoService.contratoEstaAtivo(contrato.getAtivo());

			CalculoJurosResponseModel calculoJuros = calculoJurosService.calculaJuros(contrato.getDataContratacao(),
					"JUROS_SIMPLES", quantidadeParcelas, contratacaoMovimento.getValorTotal());

			BigDecimal valorParcela = ContratoService.recalculaValorParcela(calculoJuros.getValorTotal(),
					quantidadeParcelas);

			var response = new AlteraQuantidadeParcelasResponseModel();

			BeanUtils.copyProperties(request, response);

			MovimentoModel movimento = ContratoService.alteraQuantidadeParcelas(ultimoMovimento,
					calculoJuros.getValorTotal(), quantidadeParcelas, valorParcela, calculoJuros.getPercentualJuros());

			response.getFinanceiro().add(movimento);

			return response;
		} catch (AditamentoException e) {
			throw new AditamentoException(e.getMessage());
		}
	}

	public AlteraDataPagamentoResponseModel alteraDataPagamento(AlteraDataPagamentoRequestModel request)
			throws AditamentoException {
		try {
			ContratoModel contrato = request.getContrato();

			MovimentoModel ultimoMovimento = request.getFinanceiro().get(request.getFinanceiro().size() - 1);

			int novaDataPagamento = request.getAditamento().getNovaDataPagamento();

			ContratoService.validaDiaPagamento(ultimoMovimento.getDiaPagamento(), novaDataPagamento);
			ContratoService.contratoEstaAtivo(contrato.getAtivo());
			ContratoService.contratoContemParcelasEmAtraso(contrato.getParcelasEmAtraso());

			var response = new AlteraDataPagamentoResponseModel();

			BeanUtils.copyProperties(request, response);

			MovimentoModel movimento = ContratoService.alteraDiaPagamento(ultimoMovimento, novaDataPagamento);

			response.getFinanceiro().add(movimento);

			return response;
		} catch (AditamentoException e) {
			throw new AditamentoException(e.getMessage());
		}
	}

}

package br.com.pos.venda.contrato.aditamento.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.pos.venda.contrato.aditamento.model.request.CalculoJurosRequestModel;
import br.com.pos.venda.contrato.aditamento.model.response.CalculoJurosResponseModel;
import br.com.pos.venda.contrato.aditamento.service.request.CalculoJurosRequestService;

@Service
public class CalculoJurosService {

	@Autowired
	private CalculoJurosRequestService calculoJurosRequestService;

	@Value("${spring.profiles.active:}")
	private String activeProfile;

	public CalculoJurosResponseModel calculaJuros(LocalDate dataContratacao, String criterioCalculo,
			Integer quantidadeParcelas, BigDecimal valorContratacao) {

		CalculoJurosRequestModel request = CalculoJurosRequestModel.builder().definirDataContratacao(dataContratacao)
				.definirCriterioCalculo(criterioCalculo).definirQuantidadeParcelas(quantidadeParcelas)
				.definirValorContratacao(valorContratacao).build();

		if (activeProfile.equals("prod")) {
			return calculoJurosRequestService.calculaJuros(request);
		} else {
			double percentualJuros = 1.93;
			double valorTotal = 0.0;

			valorTotal = valorContratacao.doubleValue()
					+ (valorContratacao.doubleValue() * (percentualJuros / 100.0) * quantidadeParcelas);

			BigDecimal retornoValorTotal = BigDecimal.valueOf(valorTotal).setScale(2, RoundingMode.HALF_EVEN);

			return CalculoJurosResponseModel.builder().valorTotal(retornoValorTotal)
					.percentualJuros(BigDecimal.valueOf(percentualJuros)).build();
		}
	}

}

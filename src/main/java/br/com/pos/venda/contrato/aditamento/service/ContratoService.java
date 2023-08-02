package br.com.pos.venda.contrato.aditamento.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import br.com.pos.venda.contrato.aditamento.constants.MensagensConst;
import br.com.pos.venda.contrato.aditamento.constants.TipoCalculo;
import br.com.pos.venda.contrato.aditamento.exception.AditamentoException;
import br.com.pos.venda.contrato.aditamento.model.ContratoModel;
import br.com.pos.venda.contrato.aditamento.model.MovimentoModel;

public class ContratoService {

	public static void validaQuantidadeParcelas(double quantidadeAtual, double quantidadeNova) {

		if (quantidadeNova < quantidadeAtual) {
			throw new AditamentoException(MensagensConst.QUANTIDADE_PARCELAS_INFERIOR_A_ANTERIOR);
		}
	}

	public static void validaDiaPagamento(int diaAtual, int diaNovo) {

		LocalDate today = LocalDate.now();
		var dataAtual = LocalDate.of(today.getYear(), today.getMonth(), diaAtual);
		var dataNova = LocalDate.of(today.getYear(), today.getMonth(), diaNovo);
		dataNova = diaNovo < diaAtual ? dataNova.plusMonths(1) : dataNova;

		long days = ChronoUnit.DAYS.between(dataAtual, dataNova);

		if (days >= 10) {
			throw new AditamentoException(MensagensConst.DIA_PAGAMENTO_MAIOR_10_DIAS);
		}
	}

	public static void contratoContemParcelasEmAtraso(Boolean contratoContemParcelasEmAtraso) {

		if (contratoContemParcelasEmAtraso.equals(true)) {
			throw new AditamentoException(MensagensConst.CONTRATO_CONTEM_PARCELAS_EM_ATRASO);
		}
	}

	public static void contratoEstaAtivo(Boolean contratoAtivo) {

		if (contratoAtivo.equals(false)) {
			throw new AditamentoException(MensagensConst.CONTRATO_INATIVO);
		}
	}

	public static BigDecimal recalculaValorParcela(BigDecimal valorTotal, Integer quantidadeParcelas) {

		return BigDecimal.valueOf((valorTotal.doubleValue() / quantidadeParcelas)).setScale(2, RoundingMode.HALF_EVEN);
	}

	public static MovimentoModel alteraDiaPagamento(MovimentoModel movimento, int diaPagamento) {

		return movimento.toBuilder().dataCalculo(LocalDate.now()).tipoCalculo(TipoCalculo.ADITAMENTO)
				.diaPagamento(diaPagamento).build();
	}

	public static MovimentoModel alteraQuantidadeParcelas(MovimentoModel movimento, BigDecimal valorTotal,
			int quantidadeParcelas, BigDecimal valorParcelas, BigDecimal percentualTaxaJuros) {

		return movimento.toBuilder().dataCalculo(LocalDate.now()).tipoCalculo(TipoCalculo.ADITAMENTO)
				.valorTotal(valorTotal).quantidadeParcelas(quantidadeParcelas).valorParcelas(valorParcelas)
				.percentualTaxaJuro(percentualTaxaJuros).build();
	}

	public static void ajustaResponseUltimoDigitoContrato(ContratoModel contrato) {

		String idcontrato = String.valueOf(contrato.getIdContrato());
		contrato.setIdContrato(Long.valueOf(idcontrato.substring(0, idcontrato.length() - 1)));
		contrato.setUltimoDigitoContrato(Integer.valueOf(idcontrato.substring(idcontrato.length() - 1)));
	}

}

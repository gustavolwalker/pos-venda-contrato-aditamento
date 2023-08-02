package br.com.rt.pos.venda.contrato.aditamento.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.pos.venda.contrato.aditamento.constants.MensagensConst;
import br.com.pos.venda.contrato.aditamento.exception.AditamentoException;
import br.com.pos.venda.contrato.aditamento.model.MovimentoModel;
import br.com.pos.venda.contrato.aditamento.model.request.AlteraDataPagamentoRequestModel;
import br.com.pos.venda.contrato.aditamento.model.request.AlteraQuantidadeParcelasRequestModel;
import br.com.pos.venda.contrato.aditamento.model.response.AlteraDataPagamentoResponseModel;
import br.com.pos.venda.contrato.aditamento.model.response.AlteraQuantidadeParcelasResponseModel;
import br.com.pos.venda.contrato.aditamento.model.response.CalculoJurosResponseModel;
import br.com.pos.venda.contrato.aditamento.service.AditamentoService;
import br.com.pos.venda.contrato.aditamento.service.CalculoJurosService;
import br.com.rt.pos.venda.contrato.aditamento.util.TestUtil;

@ExtendWith(SpringExtension.class)
public class AditamentoServiceTest {

	@InjectMocks
	AditamentoService aditamentoService;

	@Mock
	CalculoJurosService calculoJurosService;

	@Test
	void deveAlterarDataPagamentoComSucesso() {

		AlteraDataPagamentoRequestModel request = TestUtil.buildSimpleRequestAlteraDataPagamento();

		AlteraDataPagamentoResponseModel response = assertDoesNotThrow(
				() -> aditamentoService.alteraDataPagamento(request));

		assertEquals(2, response.getFinanceiro().size());
	}

	@Test
	void deveLancarExpetionAoAlterarDataPagamentoMairQue10Dias() {

		AlteraDataPagamentoRequestModel request = TestUtil.buildSimpleRequestAlteraDataPagamento();
		request.getAditamento().setNovaDataPagamento(2);

		Throwable response = assertThrows(AditamentoException.class,
				() -> aditamentoService.alteraDataPagamento(request));

		assertEquals(MensagensConst.DIA_PAGAMENTO_MAIOR_10_DIAS, response.getMessage());
	}

	@Test
	void deveLancarExpetionAoAlterarDataPagamentoContratoInativo() {

		AlteraDataPagamentoRequestModel request = TestUtil.buildSimpleRequestAlteraDataPagamento();
		request.getContrato().setAtivo(false);

		Throwable response = assertThrows(AditamentoException.class,
				() -> aditamentoService.alteraDataPagamento(request));

		assertEquals(MensagensConst.CONTRATO_INATIVO, response.getMessage());
	}

	@Test
	void deveLancarExpetionAoAlterarDataPagamentoContratoParcelasAtrasadas() {

		AlteraDataPagamentoRequestModel request = TestUtil.buildSimpleRequestAlteraDataPagamento();
		request.getContrato().setParcelasEmAtraso(true);

		Throwable response = assertThrows(AditamentoException.class,
				() -> aditamentoService.alteraDataPagamento(request));

		assertEquals(MensagensConst.CONTRATO_CONTEM_PARCELAS_EM_ATRASO, response.getMessage());
	}

	@Test
	void deveAlterarQuantidadeParcelasComSucesso() {

		CalculoJurosResponseModel calculoJuros = CalculoJurosResponseModel.builder().valorTotal(BigDecimal.TEN)
				.percentualJuros(BigDecimal.ONE).build();

		when(calculoJurosService.calculaJuros(any(), any(), any(), any())).thenReturn(calculoJuros);

		AlteraQuantidadeParcelasRequestModel request = TestUtil.buildSimpleRequestAlteraQuantidadeParcelas();

		AlteraQuantidadeParcelasResponseModel response = assertDoesNotThrow(
				() -> aditamentoService.alteraQuantidadeParcelas(request));

		assertEquals(2, response.getFinanceiro().size());

		MovimentoModel ultimoMovimento = response.getFinanceiro().get(1);

		assertEquals(calculoJuros.getValorTotal(), ultimoMovimento.getValorTotal());
	}

	@Test
	void deveLancarExpetionAoAlterarQuantidadeParcelasInferiorAtual() {

		AlteraQuantidadeParcelasRequestModel request = TestUtil.buildSimpleRequestAlteraQuantidadeParcelas();
		request.getAditamento().setNovaQuantidadeParcelas(1);

		Throwable response = assertThrows(AditamentoException.class,
				() -> aditamentoService.alteraQuantidadeParcelas(request));

		assertEquals(MensagensConst.QUANTIDADE_PARCELAS_INFERIOR_A_ANTERIOR, response.getMessage());
	}

}

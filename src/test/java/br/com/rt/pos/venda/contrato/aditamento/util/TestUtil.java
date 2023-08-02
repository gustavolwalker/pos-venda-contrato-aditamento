package br.com.rt.pos.venda.contrato.aditamento.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import br.com.pos.venda.contrato.aditamento.model.request.AlteraDataPagamentoRequestModel;
import br.com.pos.venda.contrato.aditamento.model.request.AlteraQuantidadeParcelasRequestModel;

public class TestUtil {

	public static final String payloadAlteraDiaPagamento = "{\"contrato\": {\"id_contrato\": 37959,"
			+ "\"numero_cpf_cnpj_cliente\": \"66273815089\",\"data_contratacao\": \"2023-03-10\","
			+ "\"ativo\": true,\"parcelas_em_atraso\": false},\"financeiro\": [{"
			+ "\"data_calculo\": \"2023-03-10\",\"tipo_calculo\": \"CONTRATACAO\","
			+ "\"valor_total\": 50000.00,\"quantidade_parcelas\": 50,\"valor_parcelas\": 1000.00,"
			+ "\"dia_pagamento\": 23,\"percentual_taxa_juro\": 1.99}],"
			+ "\"aditamento\": {\"nova_data_pagamento\": 1}}";

	public static final String payloadAlteraQuantidadeParcelas = "{\"contrato\": {\"id_contrato\": 37959,"
			+ "\"numero_cpf_cnpj_cliente\": \"66273815089\",\"data_contratacao\": \"2023-03-10\","
			+ "\"ativo\": true,\"parcelas_em_atraso\": false},\"financeiro\": [{"
			+ "\"data_calculo\": \"2023-03-10\",\"tipo_calculo\": \"CONTRATACAO\","
			+ "\"valor_total\": 50000.00,\"quantidade_parcelas\": 50,\"valor_parcelas\": 1000.00,"
			+ "\"dia_pagamento\": 23,\"percentual_taxa_juro\": 1.99}],"
			+ "\"aditamento\": {\"nova_quantidade_parcelas\": 56}}";

	public static AlteraDataPagamentoRequestModel buildSimpleRequestAlteraDataPagamento() {

		ObjectMapper mapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
		mapper.findAndRegisterModules();
		AlteraDataPagamentoRequestModel request;
		try {
			request = mapper.readValue(payloadAlteraDiaPagamento, AlteraDataPagamentoRequestModel.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			request = null;
		}

		return request;
	}

	public static AlteraQuantidadeParcelasRequestModel buildSimpleRequestAlteraQuantidadeParcelas() {

		ObjectMapper mapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
		mapper.findAndRegisterModules();
		AlteraQuantidadeParcelasRequestModel request;
		try {
			request = mapper.readValue(payloadAlteraQuantidadeParcelas, AlteraQuantidadeParcelasRequestModel.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			request = null;
		}

		return request;
	}

}

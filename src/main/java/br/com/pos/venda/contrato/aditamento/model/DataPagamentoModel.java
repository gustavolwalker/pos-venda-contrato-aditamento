package br.com.pos.venda.contrato.aditamento.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataPagamentoModel {

	@NotNull(message = "O campo 'nova_data_pagamento' é obrigatório")
	@Range(min = 1, max = 31, message = "O campo 'nova_data_pagamento' deve ser um dia válido entre 1 e 31")
	private Integer novaDataPagamento;
}

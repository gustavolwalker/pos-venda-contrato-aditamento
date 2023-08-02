package br.com.pos.venda.contrato.aditamento.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
public class QuantidadeParcelasModel {

	@NotNull(message = "O campo 'nova_quantidade_parcelas' é obrigatório")
	@Min(value = 1, message = "O campo 'nova_quantidade_parcelas' deve ser um número maior que zero")
	private Integer novaQuantidadeParcelas;
}

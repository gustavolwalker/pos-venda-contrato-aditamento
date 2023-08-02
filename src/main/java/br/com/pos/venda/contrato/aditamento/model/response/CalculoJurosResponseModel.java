package br.com.pos.venda.contrato.aditamento.model.response;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonTypeName;

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

@JsonTypeName("data")
public class CalculoJurosResponseModel {

	@NotNull
	private BigDecimal percentualJuros;

	@NotNull
	private BigDecimal valorTotal;
}

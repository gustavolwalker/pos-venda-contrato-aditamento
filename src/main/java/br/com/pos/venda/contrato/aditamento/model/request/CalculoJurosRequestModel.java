package br.com.pos.venda.contrato.aditamento.model.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
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

@JsonTypeName("contrato")
public class CalculoJurosRequestModel {

	@NotBlank
	private LocalDate definirDataContratacao;

	@NotBlank
	private String definirCriterioCalculo;

	@Min(1)
	private Integer definirQuantidadeParcelas;

	@NotNull
	private BigDecimal definirValorContratacao;

}

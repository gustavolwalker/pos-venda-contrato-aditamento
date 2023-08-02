package br.com.pos.venda.contrato.aditamento.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.pos.venda.contrato.aditamento.constants.TipoCalculo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class MovimentoModel {

	private LocalDate dataCalculo;

	private TipoCalculo tipoCalculo;

	@NotNull(message = "O campo 'valor_total' é obrigatório")
	@Positive(message = "O valor do campo 'valor_total' deve ser positivo")
	private BigDecimal valorTotal;

	private int quantidadeParcelas;

	@NotNull(message = "O campo 'valor_parcelas' é obrigatório")
	@Positive(message = "O valor do campo 'valor_parcelas' deve ser positivo")
	private BigDecimal valorParcelas;

	private int diaPagamento;

	@NotNull(message = "O campo 'percentual_taxa_juro' é obrigatório")
	@Positive(message = "O valor do campo 'percentual_taxa_juro' deve ser positivo")
	private BigDecimal percentualTaxaJuro;
}

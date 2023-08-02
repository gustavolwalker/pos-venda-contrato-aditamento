package br.com.pos.venda.contrato.aditamento.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AditamentoAbstractModel {

	@NotNull(message = "O campo 'contrato' é obrigatório")
	@Valid
	private ContratoModel contrato;
	@NotNull(message = "O campo 'financeiro' é obrigatório")
	@Valid
	private List<MovimentoModel> financeiro;
}

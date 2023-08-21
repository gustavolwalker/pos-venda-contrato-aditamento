package br.com.pos.venda.contrato.aditamento.model.request;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.pos.venda.contrato.aditamento.model.AditamentoAbstractModel;
import br.com.pos.venda.contrato.aditamento.model.QuantidadeParcelasModel;
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

public class AlteraQuantidadeParcelasRequestModel extends AditamentoAbstractModel implements Serializable {

	private static final long serialVersionUID = -8094185587316098411L;
	@NotNull(message = "O campo 'aditamento' é obrigatório")
	@Valid
	private QuantidadeParcelasModel aditamento;

}

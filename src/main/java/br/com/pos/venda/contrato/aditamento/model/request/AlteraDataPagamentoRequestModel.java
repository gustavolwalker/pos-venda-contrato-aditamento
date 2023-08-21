package br.com.pos.venda.contrato.aditamento.model.request;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.pos.venda.contrato.aditamento.model.AditamentoAbstractModel;
import br.com.pos.venda.contrato.aditamento.model.DataPagamentoModel;
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
public class AlteraDataPagamentoRequestModel extends AditamentoAbstractModel implements Serializable {

	private static final long serialVersionUID = -3422738271568473448L;
	@NotNull(message = "O campo 'aditamento' é obrigatório")
	@Valid
	private DataPagamentoModel aditamento;
}

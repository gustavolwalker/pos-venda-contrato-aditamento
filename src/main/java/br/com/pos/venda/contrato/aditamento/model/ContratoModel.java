package br.com.pos.venda.contrato.aditamento.model;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
public class ContratoModel {

	@NotNull(message = "O campo 'id_contrato' é obrigatório")
	private Long idContrato;

	private Integer ultimoDigitoContrato;

	@NotBlank(message = "O campo 'numero_cpf_cnpj_cliente' é obrigatório")
	@Pattern(regexp = "[0-9]{11,14}", message = "O campo 'numero_cpf_cnpj_cliente' deve conter apenas números e ter no mínimo 11 e no máximo 14 digitos")
	private String numeroCpfCnpjCliente;

	private LocalDate dataContratacao;

	@NotNull(message = "O campo 'ativo' é obrigatório")
	private Boolean ativo;

	@NotNull(message = "O campo 'parcelas_em_atraso' é obrigatório")
	private Boolean parcelasEmAtraso;

}

package br.com.pos.venda.contrato.aditamento.constants;

public class MensagensConst {

	public static final String QUANTIDADE_PARCELAS_INFERIOR_A_ANTERIOR = "A quantidade de parcelas não pode ser inferior a atual para realizar a operação";
	public static final String DIA_PAGAMENTO_MAIOR_10_DIAS = "O dia de pagamento informado não pode estar mais que 10 dias adiante do dia atual de pagamento do contrato";
	public static final String CONTRATO_CONTEM_PARCELAS_EM_ATRASO = "O contrato não pode ter parcelas em atraso para realizar a operação";
	public static final String CONTRATO_INATIVO = "O contrato precisa estar ativo para realizar a operação";

}

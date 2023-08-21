package br.com.pos.venda.contrato.aditamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PosVendaContratoAditamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PosVendaContratoAditamentoApplication.class, args);
	}
}

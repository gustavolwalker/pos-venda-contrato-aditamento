package br.com.rt.pos.venda.contrato.aditamento;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.pos.venda.contrato.aditamento.PosVendaContratoAditamentoApplication;

@SpringBootTest(classes = PosVendaContratoAditamentoApplication.class)
@ActiveProfiles("test")
class PosVendaContratoAditamentoApplicationTests {

	@Test
	void contextLoads() {

		assertTrue(true);
	}
}

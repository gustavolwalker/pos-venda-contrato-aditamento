package br.com.rt.pos.venda.contrato.aditamento;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import br.com.pos.venda.contrato.aditamento.PosVendaContratoAditamentoApplication;
import br.com.pos.venda.contrato.aditamento.service.AditamentoService;
import br.com.rt.pos.venda.contrato.aditamento.util.TestUtil;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PosVendaContratoAditamentoApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ApiAditamentoTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AditamentoService aditamentoService;

	@Test
	void deveChamarApiAlteraDiaPagamento() throws Exception {

		mockMvc.perform(
				post("/aditamento/altera-dia-pagamento").header("itau-pos-venda-teste", UUID.randomUUID().toString())
						.contentType(MediaType.APPLICATION_JSON).content(TestUtil.payloadAlteraDiaPagamento))
				.andDo(print()).andExpect(status().isOk());

	}

	@Test
	void deveChamarApiAlteraQuantidadeParcelas() throws Exception {

		mockMvc.perform(post("/aditamento/altera-quantidade-parcelas")
				.header("itau-pos-venda-teste", UUID.randomUUID().toString()).contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.payloadAlteraQuantidadeParcelas)).andDo(print()).andExpect(status().isOk());

	}

}

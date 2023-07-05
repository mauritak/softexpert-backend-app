package br.com.softexpert.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.mockito.InjectMocks;

import br.com.softexpert.domain.model.Rateio;
import br.com.softexpert.domain.service.CalculadoraService;

public class CalculadoraServiceTest {

	@InjectMocks
	private CalculadoraService service = new CalculadoraService();

	@Test
	public void whenCalculoRateioComSucesso() {
		Rateio rateio = service.processarRateio(PedidoMock.get(), "7d9f0335-8dcc-4054-9bf9-0dbd61d36906", false);

		Map<String, BigDecimal> resultadoEsperado = new HashMap<String, BigDecimal>();

		resultadoEsperado.put("Mauricio", BigDecimal.valueOf(31.92));
		resultadoEsperado.put("Amigo", BigDecimal.valueOf(6.08));

		assertThat(rateio.getValorAPagarPorPessoa().size(), is(2));
		assertThat(rateio.getValorAPagarPorPessoa(), is(resultadoEsperado));

	}

}

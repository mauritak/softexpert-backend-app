package br.com.softexpert.domain.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softexpert.domain.exception.CalculoException;
import br.com.softexpert.domain.model.ItemPedido;
import br.com.softexpert.domain.model.Pedido;
import br.com.softexpert.domain.model.Rateio;
import ch.qos.logback.classic.Logger;

@Service
public class CalculadoraService {

	private static final Logger log = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(CalculadoraService.class);
	
	@Autowired
	private PixUrlService cobrancaService;

	public Rateio processarRateio(Pedido pedido, final String chave, boolean habilitarLinkPagamento) throws CalculoException {
		log.info("processando calculo do rateio...");
		
		Map<String, BigDecimal> valorPorPessoa = new HashMap<>();
		
		final BigDecimal valorTotal = pedido.getValorTotal();		
		final BigDecimal valorTotalAPagar = pedido.getValorTotalAPagar();	

		if (valorTotalAPagar.doubleValue() <= 0) {
			throw new CalculoException("Que sorte! O valor total a pagar e zero, seus amigos não vao precisar pagar nada!");
		}
		
		Map<String, BigDecimal> itensAgrupadoPorPessoa = agruparItensPorPessoa(pedido);		
		
		itensAgrupadoPorPessoa.entrySet().stream().forEach(a -> {
			BigDecimal valorAPagar = calcularValorAPagarPorPessoa(calcularPercentualPorPessoa(a.getValue(), valorTotal), valorTotalAPagar);

			valorPorPessoa.put(a.getKey(), valorAPagar);
		});			
		
		Rateio rateio = Rateio.builder()
				.valorTotalAPagar(pedido.getValorTotalAPagar())
				.valorAPagarPorPessoa(valorPorPessoa)				
				.build();

		if(habilitarLinkPagamento) {			
			rateio.setLinksPagamento(cobrancaService.gerarURLPix(valorPorPessoa, chave));
		}		

		log.info("calculo do rateio concluido com sucesso");
		
		return rateio;
	}	
	
	private Map<String, BigDecimal> agruparItensPorPessoa(Pedido pedido){
		return pedido.getItensDoPedido().stream()
				.collect(Collectors.groupingBy(ItemPedido::getQuemPediu, Collectors.reducing(BigDecimal.ZERO, ItemPedido::getValor, BigDecimal::add)));
	}
	
	private BigDecimal calcularPercentualPorPessoa(BigDecimal valorPessoa, BigDecimal valorTotalItens) {
		return valorPessoa.multiply(BigDecimal.valueOf(100)).divide(valorTotalItens, 1, RoundingMode.HALF_UP);
	}
	
	private BigDecimal calcularValorAPagarPorPessoa(BigDecimal percentual, BigDecimal valorTotalAPagar) {
		return percentual.divide(BigDecimal.valueOf(100)).multiply(valorTotalAPagar);
	}
}

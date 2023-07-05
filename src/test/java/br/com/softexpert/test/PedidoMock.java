package br.com.softexpert.test;

import java.math.BigDecimal;
import java.util.ArrayList;

import br.com.softexpert.domain.model.ItemPedido;
import br.com.softexpert.domain.model.Pedido;

public class PedidoMock {

	public static Pedido get() {		
		Pedido pedido = Pedido.builder()
			.itensDoPedido(new ArrayList<>())
			.valorDesconto(BigDecimal.valueOf(20))
			.valorTaxaEntrega(BigDecimal.valueOf(8))			
			.build();
		
		ItemPedido item1 = ItemPedido.builder()
				.quemPediu("Mauricio")
				.descricao("Hamburger")
				.valor(BigDecimal.valueOf(40))
				.build();
		
		ItemPedido item2 = ItemPedido.builder()
				.quemPediu("Mauricio")
				.descricao("Sobremesa")
				.valor(BigDecimal.valueOf(2))
				.build();
		
		ItemPedido item3 = ItemPedido
				.builder()
				.quemPediu("Amigo")
				.descricao("Sanduiche (Amigo)")
				.valor(BigDecimal.valueOf(8))
				.build();
		
		pedido.getItensDoPedido().add(item1);
		pedido.getItensDoPedido().add(item2);
		pedido.getItensDoPedido().add(item3);
		
		return pedido;
	}
}

package br.com.softexpert.domain.model;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Pedido {

	@Valid
	@NotNull
	private List<ItemPedido> itensDoPedido;

	@NotNull(message = "Campo Obrigatorio. Informe o valor da taxa de entrega")
	private BigDecimal valorTaxaEntrega;

	@NotNull(message = "Campo Obrigatorio. Informe o valor do desconto, se não houver descontro, informe zero")
	private BigDecimal valorDesconto;

	@JsonIgnore
	public BigDecimal getValorTotal() {
		return this.itensDoPedido.stream().map(ItemPedido::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	@JsonIgnore
	public BigDecimal getValorTotalAPagar() {
		return BigDecimal.ZERO.add(this.getValorTotal()).add(valorTaxaEntrega).subtract(valorDesconto);
	}
}

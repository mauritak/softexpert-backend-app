package br.com.softexpert.domain.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class ItemPedido {

	@NotBlank(message = "Campo Obrigatorio. Informe uma descricao para o item pedido")
	private String descricao;

	@NotNull(message = "Campo Obrigatorio. Informe o valor do item pedido")
	private BigDecimal valor;

	@NotBlank(message = "Campo Obrigatorio. Informe o nome de quem pediu o item")
	private String quemPediu;

	public String getQuemPediu() {
		return StringUtils.isEmpty(this.quemPediu) ? StringUtils.EMPTY : StringUtils.trim(quemPediu);
	}
}

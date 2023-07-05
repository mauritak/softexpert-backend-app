package br.com.softexpert.controller.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.softexpert.domain.model.Pedido;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RateioRequest {

	@ApiModelProperty(position = 1, notes = "Detalhes do pedido que sera rateado entre os amigos", required = true)
	@Valid
	@NotNull
	private Pedido pedidoParaRatearComAmigos;

	@ApiModelProperty(position = 2, example = "mauricio.takabayashi@gmail.com", notes = "Chave pix para receber o pagamento dos amigos", required = true)
	@Valid
	@NotNull
	private String chave;
}

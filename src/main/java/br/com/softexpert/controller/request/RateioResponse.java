package br.com.softexpert.controller.request;

import br.com.softexpert.domain.model.Rateio;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RateioResponse {

	@ApiModelProperty(position = 2, notes = "Detalhes do valor por pessoa devera pagar", required = true)
	private Rateio rateio;
}

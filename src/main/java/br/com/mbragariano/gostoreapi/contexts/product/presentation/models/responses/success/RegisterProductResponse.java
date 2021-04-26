package br.com.mbragariano.gostoreapi.contexts.product.presentation.models.responses.success;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
public class RegisterProductResponse {

  public String id;

  public String name;

  public BigDecimal price;

  public String description;

}

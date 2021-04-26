package br.com.mbragariano.gostoreapi.contexts.product.presentation.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class RegisterProductRequest {

  public String name;

  public BigDecimal price;

  public String description;

}

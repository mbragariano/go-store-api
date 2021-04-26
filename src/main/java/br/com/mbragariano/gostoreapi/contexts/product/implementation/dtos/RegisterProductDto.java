package br.com.mbragariano.gostoreapi.contexts.product.implementation.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
public class RegisterProductDto {

  public String name;

  public BigDecimal price;

  public String description;

}

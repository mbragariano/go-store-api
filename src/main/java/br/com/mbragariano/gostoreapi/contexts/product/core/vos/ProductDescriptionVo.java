package br.com.mbragariano.gostoreapi.contexts.product.core.vos;

import io.vavr.control.Validation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public class ProductDescriptionVo {

  private final String value;

  private static final String FIRST_VALIDATION_MESSAGE = "The product description must not be null";
  private static final String SECOND_VALIDATION_MESSAGE = "The product description must not be blank";

  public static Validation<String, ProductDescriptionVo> create(final String value) {
    if (Objects.isNull(value))
      return Validation.invalid(FIRST_VALIDATION_MESSAGE);

    if (value.isEmpty() || value.isBlank())
      return Validation.invalid(SECOND_VALIDATION_MESSAGE);

    return Validation.valid(new ProductDescriptionVo(value));
  }

}

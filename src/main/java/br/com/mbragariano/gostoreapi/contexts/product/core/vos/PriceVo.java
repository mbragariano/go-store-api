package br.com.mbragariano.gostoreapi.contexts.product.core.vos;

import io.vavr.control.Validation;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@AllArgsConstructor
public class PriceVo {

  private final BigDecimal value;

  private static final String FIRST_VALIDATION_MESSAGE = "The product price must not be null";
  private static final String SECOND_VALIDATION_MESSAGE = "The product price must not be equal or less tan zero";

  public static Validation<String, PriceVo> create(final BigDecimal value) {
    if (Objects.isNull(value))
      return Validation.invalid(FIRST_VALIDATION_MESSAGE);

    final var valueIsEqualsOrLessTanZero = value.compareTo(BigDecimal.ZERO);

    if (valueIsEqualsOrLessTanZero < 0 || valueIsEqualsOrLessTanZero == 0)
      return Validation.invalid(SECOND_VALIDATION_MESSAGE);

    return Validation.valid(new PriceVo(value));
  }

}

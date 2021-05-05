package br.com.mbragariano.gostoreapi.contexts.product.core.vos;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProductPriceVoTest {

  private static final BigDecimal PRICE_VALUE = new BigDecimal("10.90");

  private static final String FIRST_VALIDATION_MESSAGE = "The product price must not be null";
  private static final String SECOND_VALIDATION_MESSAGE = "The product price must not be equal or less tan zero";

  @Test
  public void create_whenProvide_nullString_shouldReturnLeft_withValidationMessage() {
    final var nameOrValidation = ProductPriceVo.create(PRICE_VALUE);

    assertThat(nameOrValidation.isValid()).isTrue();
    assertThat(nameOrValidation.get().getValue()).isEqualTo(PRICE_VALUE);
  }

  @Test
  public void create_whenProvide_blankString_shouldReturnLeft_withValidationMessage() {
    final var nameOrValidation = ProductPriceVo.create(null);

    assertThat(nameOrValidation.isInvalid()).isTrue();
    assertThat(nameOrValidation.getError()).isEqualTo(FIRST_VALIDATION_MESSAGE);
  }

  @Test
  public void create_whenProvide_nonBlankString_and_nonNullString_shouldReturnRight_withPriceVo() {
    final var nameOrValidation = ProductPriceVo.create(new BigDecimal("0"));

    assertThat(nameOrValidation.isInvalid()).isTrue();
    assertThat(nameOrValidation.getError()).isEqualTo(SECOND_VALIDATION_MESSAGE);
  }

}

package br.com.mbragariano.gostoreapi.contexts.product.core.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class ProductEntityTest {

  @ValueSource(booleans = {true, false})
  @ParameterizedTest(name = "create_whenProvide_invalidValues_shouldReturnLeft_withValidationMessages: nullable = {0}")
  public void create_whenProvide_invalidValues_shouldReturnLeft_withValidationMessages(boolean nullable) {
    final var nameValue = nullable ? null : "";
    final var descriptionValue = nullable ? null : "";
    final var priceValue = nullable ? null : new BigDecimal("0");

    final var productOrValidations = ProductEntity.create(nameValue, priceValue, descriptionValue);

    assertThat(productOrValidations.isInvalid()).isTrue();
    assertThat(productOrValidations.getError(), hasSize(3));
  }

  @Test
  public void create_whenProvide_validValues_shouldReturnRight_withProductEntityInstance() {
    final var name = "Sabonete Nivea";
    final var price = new BigDecimal("10.90");
    final var description = "Novo sabonete nivea desenvolvido a base de seiva";

    final var productOrValidations = ProductEntity.create(name, price, description);

    assertThat(productOrValidations.isValid()).isTrue();

    final var productEntity = productOrValidations.get();

    assertThat(productEntity.getNameValue()).isEqualTo(name);
    assertThat(productEntity.getPriceValue()).isEqualTo(price);
    assertThat(productEntity.getDescriptionValue()).isEqualTo(description);
  }

}

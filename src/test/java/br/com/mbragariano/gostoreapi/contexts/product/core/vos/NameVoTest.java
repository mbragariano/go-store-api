package br.com.mbragariano.gostoreapi.contexts.product.core.vos;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NameVoTest {

  private static final String NAME_VALUE = "Sabonete Nivea";

  private static final String FIRST_VALIDATION_MESSAGE = "The product name must not be null";
  private static final String SECOND_VALIDATION_MESSAGE = "The product name must not be blank";

  @Test
  public void create_whenProvide_nullString_shouldReturnLeft_withValidationMessage() {
    final var nameOrValidation = NameVo.create(NAME_VALUE);

    assertThat(nameOrValidation.isValid()).isTrue();
    assertThat(nameOrValidation.get().getValue()).isEqualTo(NAME_VALUE);
  }

  @Test
  public void create_whenProvide_blankString_shouldReturnLeft_withValidationMessage() {
    final var nameOrValidation = NameVo.create(null);

    assertThat(nameOrValidation.isInvalid()).isTrue();
    assertThat(nameOrValidation.getError()).isEqualTo(FIRST_VALIDATION_MESSAGE);
  }

  @Test
  public void create_whenProvide_nonBlankString_and_nonNullString_shouldReturnRight_withNameVo() {
    final var nameOrValidation = NameVo.create("");

    assertThat(nameOrValidation.isInvalid()).isTrue();
    assertThat(nameOrValidation.getError()).isEqualTo(SECOND_VALIDATION_MESSAGE);
  }

}

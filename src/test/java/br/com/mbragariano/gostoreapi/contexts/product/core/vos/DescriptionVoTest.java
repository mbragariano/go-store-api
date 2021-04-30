package br.com.mbragariano.gostoreapi.contexts.product.core.vos;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DescriptionVoTest {

  private static final String DESCRIPTION_VALUE = "Novo sabonete nivea desenvolvido a base de seiva";

  private static final String FIRST_VALIDATION_MESSAGE = "The product description must not be null";
  private static final String SECOND_VALIDATION_MESSAGE = "The product description must not be blank";

  @Test
  public void create_whenProvide_nullString_shouldReturnLeft_withValidationMessage() {
    final var descriptionOrValidation = DescriptionVo.create(DESCRIPTION_VALUE);

    assertThat(descriptionOrValidation.isValid()).isTrue();
    assertThat(descriptionOrValidation.get().getValue()).isEqualTo(DESCRIPTION_VALUE);
  }

  @Test
  public void create_whenProvide_blankString_shouldReturnLeft_withValidationMessage() {
    final var descriptionOrValidation = DescriptionVo.create(null);

    assertThat(descriptionOrValidation.isInvalid()).isTrue();
    assertThat(descriptionOrValidation.getError()).isEqualTo(FIRST_VALIDATION_MESSAGE);
  }

  @Test
  public void create_whenProvide_nonBlankString_and_nonNullString_shouldReturnRight_withDescriptionVo() {
    final var descriptionOrValidation = DescriptionVo.create("");

    assertThat(descriptionOrValidation.isInvalid()).isTrue();
    assertThat(descriptionOrValidation.getError()).isEqualTo(SECOND_VALIDATION_MESSAGE);
  }

}

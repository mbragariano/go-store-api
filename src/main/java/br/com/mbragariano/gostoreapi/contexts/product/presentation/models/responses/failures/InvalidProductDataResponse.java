package br.com.mbragariano.gostoreapi.contexts.product.presentation.models.responses.failures;

import br.com.mbragariano.gostoreapi.commons.presentation.responses.contract.BaseFailureResponse;
import lombok.Builder;

import java.util.List;

public class InvalidProductDataResponse extends BaseFailureResponse {

  public List<String> validations;

  @Builder
  public InvalidProductDataResponse(
    String message,
    String description,
    String developerMessage,
    List<String> validations
  ) {
    super(message, description, developerMessage);

    this.validations = validations;
  }

}

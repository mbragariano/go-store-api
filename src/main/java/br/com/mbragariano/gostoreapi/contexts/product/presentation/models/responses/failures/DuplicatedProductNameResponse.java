package br.com.mbragariano.gostoreapi.contexts.product.presentation.models.responses.failures;

import br.com.mbragariano.gostoreapi.commons.presentation.responses.contract.BaseFailureResponse;
import lombok.Builder;

public class DuplicatedProductNameResponse extends BaseFailureResponse {

  @Builder
  public DuplicatedProductNameResponse(String message, String description, String developerMessage) {
    super(message, description, developerMessage);
  }

}

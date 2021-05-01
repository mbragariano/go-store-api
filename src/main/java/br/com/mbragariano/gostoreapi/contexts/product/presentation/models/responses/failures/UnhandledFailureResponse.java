package br.com.mbragariano.gostoreapi.contexts.product.presentation.models.responses.failures;

import br.com.mbragariano.gostoreapi.commons.presentation.responses.contract.BaseFailureResponse;
import br.com.mbragariano.gostoreapi.contexts.product.implementation.failures.contract.RegisterProductFailure;
import lombok.Builder;

public class UnhandledFailureResponse extends BaseFailureResponse {

  public RegisterProductFailure unhandledFailure;

  @Builder
  public UnhandledFailureResponse(
    String message,
    String description,
    String developerMessage,
    RegisterProductFailure unhandledFailure) {
    super(message, description, developerMessage);

    this.unhandledFailure = unhandledFailure;
  }

}

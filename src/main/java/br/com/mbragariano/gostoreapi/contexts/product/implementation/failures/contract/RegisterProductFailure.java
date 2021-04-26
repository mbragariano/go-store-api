package br.com.mbragariano.gostoreapi.contexts.product.implementation.failures.contract;

import br.com.mbragariano.gostoreapi.commons.implementation.failures.BaseFailure;

public abstract class RegisterProductFailure extends BaseFailure {

  public RegisterProductFailure(String message, String description, String developerMessage) {
    super(message, description, developerMessage);
  }

}

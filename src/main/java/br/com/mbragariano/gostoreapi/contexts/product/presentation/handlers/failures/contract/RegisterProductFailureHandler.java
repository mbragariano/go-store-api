package br.com.mbragariano.gostoreapi.contexts.product.presentation.handlers.failures.contract;

import br.com.mbragariano.gostoreapi.commons.presentation.handlers.failures.contract.BaseFailureHandler;
import br.com.mbragariano.gostoreapi.contexts.product.implementation.failures.contract.RegisterProductFailure;

public abstract class RegisterProductFailureHandler extends BaseFailureHandler<RegisterProductFailureHandler, RegisterProductFailure> {

  public RegisterProductFailureHandler(RegisterProductFailureHandler nextFailureHandler) {
    super(nextFailureHandler);
  }

}

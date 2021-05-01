package br.com.mbragariano.gostoreapi.contexts.product.presentation.handlers.failures.concrete;

import br.com.mbragariano.gostoreapi.contexts.product.implementation.failures.concrete.InvalidProductDataFailure;
import br.com.mbragariano.gostoreapi.contexts.product.implementation.failures.contract.RegisterProductFailure;
import br.com.mbragariano.gostoreapi.contexts.product.presentation.handlers.failures.contract.RegisterProductFailureHandler;
import br.com.mbragariano.gostoreapi.contexts.product.presentation.mappers.RegisterProductPresentationMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class InvalidProductDataFailureHandler extends RegisterProductFailureHandler {

  public InvalidProductDataFailureHandler(
    @Qualifier("unhandledFailureHandler") RegisterProductFailureHandler nextFailureHandler
  ) {
    super(nextFailureHandler);
  }

  @Override
  public ResponseEntity<?> handle(RegisterProductFailure baseFailure) {
    if (!(baseFailure instanceof InvalidProductDataFailure))
      return this.getNextFailureHandler().handle(baseFailure);

    final var failureResponse = RegisterProductPresentationMapper.mapToFailureResponse((InvalidProductDataFailure) baseFailure);

    return ResponseEntity.badRequest()
      .headers(this.getDefaultHeaders())
      .body(failureResponse);
  }
}

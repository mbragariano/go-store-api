package br.com.mbragariano.gostoreapi.contexts.product.presentation.handlers.failures.concrete;

import br.com.mbragariano.gostoreapi.contexts.product.implementation.failures.concrete.DuplicatedProductNameFailure;
import br.com.mbragariano.gostoreapi.contexts.product.implementation.failures.contract.RegisterProductFailure;
import br.com.mbragariano.gostoreapi.contexts.product.presentation.handlers.failures.contract.RegisterProductFailureHandler;
import br.com.mbragariano.gostoreapi.contexts.product.presentation.mappers.RegisterProductPresentationMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DuplicatedProductNameFailureHandler extends RegisterProductFailureHandler {

  public DuplicatedProductNameFailureHandler(
    @Qualifier("invalidProductDataFailureHandler") RegisterProductFailureHandler registerProductFailureHandler
  ) {
    super(registerProductFailureHandler);
  }

  @Override
  public ResponseEntity<?> handle(RegisterProductFailure baseFailure) {
    if (!(baseFailure instanceof DuplicatedProductNameFailure))
      return this.getNextFailureHandler().handle(baseFailure);

    final var failureResponse = RegisterProductPresentationMapper.mapToFailureResponse((DuplicatedProductNameFailure) baseFailure);

    return ResponseEntity.status(HttpStatus.CONFLICT).body(failureResponse);
  }

}

package br.com.mbragariano.gostoreapi.contexts.product.presentation.handlers.failures.concrete;

import br.com.mbragariano.gostoreapi.contexts.product.implementation.failures.contract.RegisterProductFailure;
import br.com.mbragariano.gostoreapi.contexts.product.presentation.handlers.failures.contract.RegisterProductFailureHandler;
import br.com.mbragariano.gostoreapi.contexts.product.presentation.mappers.RegisterProductPresentationMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UnhandledFailureHandler extends RegisterProductFailureHandler {

  public UnhandledFailureHandler() {
    super(null);
  }

  @Override
  public ResponseEntity<?> handle(RegisterProductFailure baseFailure) {
    final var failureResponse = RegisterProductPresentationMapper.mapToUnhandledFailureHandler(baseFailure);
    final var httpHeaders = this.getDefaultHeaders();

    return new ResponseEntity<>(failureResponse, httpHeaders, HttpStatus.NOT_FOUND);
  }

}

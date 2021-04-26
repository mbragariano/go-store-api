package br.com.mbragariano.gostoreapi.contexts.product.presentation.handlers.failures.concrete;

import br.com.mbragariano.gostoreapi.contexts.product.implementation.failures.contract.RegisterProductFailure;
import br.com.mbragariano.gostoreapi.contexts.product.presentation.handlers.failures.contract.RegisterProductFailureHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class NotFoundFailureHandler extends RegisterProductFailureHandler {

  public NotFoundFailureHandler() {
    super(null);
  }

  @Override
  public ResponseEntity<?> handle(RegisterProductFailure baseFailure) {
    return ResponseEntity.notFound().build();
  }

}

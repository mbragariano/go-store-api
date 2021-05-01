package br.com.mbragariano.gostoreapi.commons.presentation.handlers.failures.contract;

import br.com.mbragariano.gostoreapi.commons.implementation.failures.BaseFailure;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Getter
@RequiredArgsConstructor
public abstract class BaseFailureHandler<T, F extends BaseFailure> {

  protected final T nextFailureHandler;

  public abstract ResponseEntity<?> handle(F baseFailure);

  protected HttpHeaders getDefaultHeaders() {
    final var httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_PROBLEM_JSON);

    return httpHeaders;
  }

}

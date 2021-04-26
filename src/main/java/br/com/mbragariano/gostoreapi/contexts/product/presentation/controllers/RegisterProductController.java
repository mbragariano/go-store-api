package br.com.mbragariano.gostoreapi.contexts.product.presentation.controllers;

import br.com.mbragariano.gostoreapi.contexts.product.implementation.services.RegisterProductService;
import br.com.mbragariano.gostoreapi.contexts.product.presentation.handlers.failures.concrete.DuplicatedProductNameFailureHandler;
import br.com.mbragariano.gostoreapi.contexts.product.presentation.mappers.RegisterProductPresentationMapper;
import br.com.mbragariano.gostoreapi.contexts.product.presentation.models.requests.RegisterProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RegisterProductController {

  private final RegisterProductService registerProductService;

  private final DuplicatedProductNameFailureHandler duplicatedProductNameFailureHandler;

  @PostMapping("/products")
  public ResponseEntity<?> handle(@RequestBody RegisterProductRequest registerProductRequest) {
    final var registerProductDto = RegisterProductPresentationMapper.mapToDto(registerProductRequest);
    final var entityOrFailure = this.registerProductService.execute(registerProductDto);

    if (entityOrFailure.isLeft())
      return this.duplicatedProductNameFailureHandler.handle(entityOrFailure.getLeft());

    final var registerProductResponse = RegisterProductPresentationMapper.mapToResponse(entityOrFailure.get());

    return ResponseEntity.status(HttpStatus.CREATED).body(registerProductResponse);
  }

}

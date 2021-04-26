package br.com.mbragariano.gostoreapi.contexts.product.presentation.mappers;

import br.com.mbragariano.gostoreapi.contexts.product.core.entities.ProductEntity;
import br.com.mbragariano.gostoreapi.contexts.product.implementation.dtos.RegisterProductDto;
import br.com.mbragariano.gostoreapi.contexts.product.implementation.failures.concrete.DuplicatedProductNameFailure;
import br.com.mbragariano.gostoreapi.contexts.product.implementation.failures.concrete.InvalidProductDataFailure;
import br.com.mbragariano.gostoreapi.contexts.product.presentation.models.requests.RegisterProductRequest;
import br.com.mbragariano.gostoreapi.contexts.product.presentation.models.responses.failures.InvalidProductDataResponse;
import br.com.mbragariano.gostoreapi.contexts.product.presentation.models.responses.failures.DuplicatedProductNameResponse;
import br.com.mbragariano.gostoreapi.contexts.product.presentation.models.responses.success.RegisterProductResponse;

public class RegisterProductPresentationMapper {

  public static RegisterProductDto mapToDto(RegisterProductRequest registerProductRequest) {
    return RegisterProductDto.builder()
      .name(registerProductRequest.name)
      .price(registerProductRequest.price)
      .description(registerProductRequest.description)
      .build();
  }

  public static RegisterProductResponse mapToResponse(ProductEntity productEntity) {
    return RegisterProductResponse.builder()
      .id(productEntity.getId())
      .name(productEntity.getNameValue())
      .price(productEntity.getPriceValue())
      .description(productEntity.getDescriptionValue())
      .build();
  }

  public static DuplicatedProductNameResponse mapToFailureResponse(DuplicatedProductNameFailure failure) {
    return DuplicatedProductNameResponse.builder()
      .message(failure.message)
      .description(failure.description)
      .developerMessage(failure.developerMessage)
      .build();
  }

  public static InvalidProductDataResponse mapToFailureResponse(InvalidProductDataFailure failure) {
    return InvalidProductDataResponse.builder()
      .message(failure.message)
      .description(failure.description)
      .validations(failure.validations)
      .developerMessage(failure.developerMessage)
      .build();
  }

}

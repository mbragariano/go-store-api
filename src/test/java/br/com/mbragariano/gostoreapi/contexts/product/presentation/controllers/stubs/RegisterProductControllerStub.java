package br.com.mbragariano.gostoreapi.contexts.product.presentation.controllers.stubs;

import br.com.mbragariano.gostoreapi.contexts.product.persistence.documents.ProductDocument;
import br.com.mbragariano.gostoreapi.contexts.product.presentation.models.requests.RegisterProductRequest;
import br.com.mbragariano.gostoreapi.contexts.product.presentation.models.responses.failures.DuplicatedProductNameResponse;
import br.com.mbragariano.gostoreapi.contexts.product.presentation.models.responses.failures.InvalidProductDataResponse;

import java.math.BigDecimal;

public class RegisterProductControllerStub {

  public static RegisterProductRequest getInvalidRegisterProductRequest(boolean nullable) {
    final var name = nullable ? null : "";
    final var description = nullable ? null : "";
    final var price = nullable ? null : new BigDecimal("0");

    return new RegisterProductRequest(name, price, description);
  }

  public static InvalidProductDataResponse getInvalidProductDataResponse() {
    return InvalidProductDataResponse.builder()
      .message("The product cannot be registered")
      .description("The entered values are invalid, see the list of validations")
      .build();
  }

  public static RegisterProductRequest getDuplicatedProductRequest() {
    final var name = "Sabonete Dove";
    final var price = new BigDecimal("14.90");
    final var description = "Novo sabonete Dove desenvolvido a base de seiva";

    return new RegisterProductRequest(name, price, description);
  }

  public static ProductDocument getDuplicatedProductDocument() {
    final var duplicatedProductRequest = getDuplicatedProductRequest();

    return ProductDocument.builder()
      .name(duplicatedProductRequest.name)
      .price(duplicatedProductRequest.price)
      .description(duplicatedProductRequest.description)
      .build();
  }

  public static DuplicatedProductNameResponse getDuplicatedProductNameResponse() {
    final var name = getDuplicatedProductRequest().name;
    final var description = String.format("Another product is registered under the name \"%s\"", name);

    return DuplicatedProductNameResponse.builder()
      .message("The product cannot not be registered")
      .description(description)
      .build();
  }

  public static RegisterProductRequest getRegisterProductRequest() {
    final var name = "Sabonete Nivea";
    final var price = new BigDecimal("10.9");
    final var description = "Novo sabonete Nivea desenvolvido a base de seiva";

    return new RegisterProductRequest(name, price, description);
  }

}

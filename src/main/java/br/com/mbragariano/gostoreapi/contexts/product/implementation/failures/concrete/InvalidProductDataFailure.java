package br.com.mbragariano.gostoreapi.contexts.product.implementation.failures.concrete;

import br.com.mbragariano.gostoreapi.contexts.product.implementation.failures.contract.RegisterProductFailure;

import java.util.List;

public class InvalidProductDataFailure extends RegisterProductFailure {

  public List<String> validations;

  private static final String MESSAGE = "The product cannot be registered";

  private static final String DESCRIPTION = "The entered values are invalid, see the list of validations";

  public InvalidProductDataFailure(List<String> validations) {
    super(MESSAGE, DESCRIPTION, null);

    this.validations = validations;
  }

}

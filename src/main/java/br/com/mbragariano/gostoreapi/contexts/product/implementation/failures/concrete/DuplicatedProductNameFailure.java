package br.com.mbragariano.gostoreapi.contexts.product.implementation.failures.concrete;

import br.com.mbragariano.gostoreapi.contexts.product.implementation.failures.contract.RegisterProductFailure;

public class DuplicatedProductNameFailure extends RegisterProductFailure {

  private static final String MESSAGE = "The product cannot not be registered";

  private static final String DESCRIPTION = "Another product is registered under the name \"%s\"";

  public DuplicatedProductNameFailure(String name) {
    super(MESSAGE, String.format(DESCRIPTION, name), null);
  }

}

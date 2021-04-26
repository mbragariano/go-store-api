package br.com.mbragariano.gostoreapi.commons.implementation.failures;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class BaseFailure {

  public String message;

  public String description;

  public String developerMessage;

}

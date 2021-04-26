package br.com.mbragariano.gostoreapi.commons.presentation.responses.contract;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class BaseFailureResponse {

  public String message;

  public String description;

  @JsonProperty("developer_message")
  public String developerMessage;

  @JsonGetter(value = "response_type")
  public String responseType() {
    return this.getClass().getSimpleName();
  }

}

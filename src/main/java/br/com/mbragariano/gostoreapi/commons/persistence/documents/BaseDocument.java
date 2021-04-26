package br.com.mbragariano.gostoreapi.commons.persistence.documents;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
public abstract class BaseDocument {

  public static final String COLLECTION_LANGUAGE = "portuguese";

  @Id
  @Field
  public String id;

}

package br.com.mbragariano.gostoreapi.contexts.product.persistence.documents;

import br.com.mbragariano.gostoreapi.commons.persistence.documents.BaseDocument;
import lombok.Builder;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;

@TypeAlias(ProductDocument.TYPE_ALIAS)
@Document(collection = "products", language = BaseDocument.COLLECTION_LANGUAGE)
public class ProductDocument extends BaseDocument {

  public static final String TYPE_ALIAS = "ProductDocument";

  @Field
  public String name;

  @Field(targetType = FieldType.DECIMAL128)
  public BigDecimal price;

  @Field
  public String description;

  @Builder
  public ProductDocument(
    final String id,
    final String name,
    final BigDecimal price,
    final String description
  ) {
    super(id);

    this.name = name;
    this.price = price;
    this.description = description;
  }

}

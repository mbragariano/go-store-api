package br.com.mbragariano.gostoreapi.contexts.product.persistence.mappers;

import br.com.mbragariano.gostoreapi.contexts.product.core.entities.ProductEntity;
import br.com.mbragariano.gostoreapi.contexts.product.persistence.documents.ProductDocument;

public class ProductPersistenceMapper {

  public static ProductDocument mapToProductDocument(final ProductEntity productEntity) {
    return ProductDocument.builder()
      .id(productEntity.getId())
      .name(productEntity.getNameValue())
      .price(productEntity.getPriceValue())
      .description(productEntity.getDescriptionValue())
      .build();
  }

}

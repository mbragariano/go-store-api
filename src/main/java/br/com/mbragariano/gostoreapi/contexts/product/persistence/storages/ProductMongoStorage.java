package br.com.mbragariano.gostoreapi.contexts.product.persistence.storages;

import br.com.mbragariano.gostoreapi.contexts.product.core.entities.ProductEntity;
import br.com.mbragariano.gostoreapi.contexts.product.implementation.ports.ProductStorage;
import br.com.mbragariano.gostoreapi.contexts.product.persistence.documents.ProductDocument;
import br.com.mbragariano.gostoreapi.contexts.product.persistence.mappers.ProductPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMongoStorage implements ProductStorage {

  private final MongoTemplate mongoTemplate;

  @Override
  public Boolean existByName(String name) {
    final var regex = "\\.*%s\\.*";
    final var formattedRegex = String.format(regex, name);

    final var criteria = Criteria.where("name").regex(formattedRegex, "i");
    final var query = new Query(criteria);

    return this.mongoTemplate.exists(query, ProductDocument.class);
  }

  @Override
  public ProductEntity register(ProductEntity productEntity) {
    final var productDocument = ProductPersistenceMapper.mapToProductDocument(productEntity);

    final var createdId = this.mongoTemplate.save(productDocument).id;
    productEntity.setId(createdId);

    return productEntity;
  }

}

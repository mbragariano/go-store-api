package br.com.mbragariano.gostoreapi.contexts.product.implementation.ports;

import br.com.mbragariano.gostoreapi.contexts.product.core.entities.ProductEntity;

public interface ProductStorage {

  Boolean existByName(String name);

  ProductEntity register(ProductEntity productEntity);

}

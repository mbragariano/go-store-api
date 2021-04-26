package br.com.mbragariano.gostoreapi.contexts.product.implementation.services;

import br.com.mbragariano.gostoreapi.contexts.product.core.entities.ProductEntity;
import br.com.mbragariano.gostoreapi.contexts.product.implementation.dtos.RegisterProductDto;
import br.com.mbragariano.gostoreapi.contexts.product.implementation.failures.concrete.DuplicatedProductNameFailure;
import br.com.mbragariano.gostoreapi.contexts.product.implementation.failures.concrete.InvalidProductDataFailure;
import br.com.mbragariano.gostoreapi.contexts.product.implementation.failures.contract.RegisterProductFailure;
import br.com.mbragariano.gostoreapi.contexts.product.implementation.ports.ProductStorage;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterProductService {

  private final ProductStorage productStorage;

  public Either<RegisterProductFailure, ProductEntity> execute(RegisterProductDto registerProductDto) {
    final var name = registerProductDto.name;

    final var entityOrValidations = ProductEntity.create(
      name,
      registerProductDto.price,
      registerProductDto.description
    );

    if (entityOrValidations.isInvalid())
      return entityOrValidations.toEither().mapLeft(InvalidProductDataFailure::new);

    final var existByName = this.productStorage.existByName(name);

    if (existByName)
      return Either.left(new DuplicatedProductNameFailure(name));

    final var registeredEntity = this.productStorage.register(entityOrValidations.get());

    return Either.right(registeredEntity);
  }

}

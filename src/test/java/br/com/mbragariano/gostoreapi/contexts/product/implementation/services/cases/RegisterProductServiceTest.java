package br.com.mbragariano.gostoreapi.contexts.product.implementation.services.cases;

import br.com.mbragariano.gostoreapi.contexts.product.core.entities.ProductEntity;
import br.com.mbragariano.gostoreapi.contexts.product.implementation.failures.concrete.DuplicatedProductNameFailure;
import br.com.mbragariano.gostoreapi.contexts.product.implementation.failures.concrete.InvalidProductDataFailure;
import br.com.mbragariano.gostoreapi.contexts.product.implementation.ports.ProductStorage;
import br.com.mbragariano.gostoreapi.contexts.product.implementation.services.RegisterProductService;
import br.com.mbragariano.gostoreapi.contexts.product.implementation.services.stubs.RegisterProductServiceStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
public class RegisterProductServiceTest {

  @Mock
  private ProductStorage productStorage;

  @InjectMocks
  private RegisterProductService registerProductService;

  @Test
  public void execute_whenProvideDto_withInvalidData_shouldReturnInvalidProductDataFailure() {
    final var registerProductDto = RegisterProductServiceStub.getInvalidRegisterProductDto();

    doReturn(Boolean.FALSE)
      .when(this.productStorage)
      .existByName(registerProductDto.name);

    final var productEntityOrFailure = this.registerProductService.execute(registerProductDto);

    assertThat(productEntityOrFailure.isLeft()).isTrue();

    final var failure = (InvalidProductDataFailure) productEntityOrFailure.getLeft();

    assertThat(failure.validations.isEmpty()).isFalse();
  }

  @Test
  public void execute_whenProvideDto_withExistentName_shouldReturnDuplicatedProductNameFailure() {
    final var registerProductDto = RegisterProductServiceStub.getRegisterProductDto();

    doReturn(Boolean.TRUE)
      .when(this.productStorage)
      .existByName(registerProductDto.name);

    final var productEntityOrFailure = this.registerProductService.execute(registerProductDto);

    assertThat(productEntityOrFailure.isLeft()).isTrue();
    assertThat(productEntityOrFailure.getLeft()).isInstanceOf(DuplicatedProductNameFailure.class);
  }

  @Test
  public void execute_whenProvideDto_withNonexistentName_and_withValidData_shouldReturnProductEntity() {
    final var registerProductDto = RegisterProductServiceStub.getRegisterProductDto();
    final var registeredProductEntity = RegisterProductServiceStub.getProductEntity();

    doReturn(Boolean.FALSE)
      .when(this.productStorage)
      .existByName(registerProductDto.name);

    doReturn(registeredProductEntity)
      .when(this.productStorage)
      .register(any(ProductEntity.class));

    final var productEntityOrFailure = this.registerProductService.execute(registerProductDto);

    assertThat(productEntityOrFailure.isRight()).isTrue();
    assertThat(productEntityOrFailure.get().getId()).isNotNull();
  }

}

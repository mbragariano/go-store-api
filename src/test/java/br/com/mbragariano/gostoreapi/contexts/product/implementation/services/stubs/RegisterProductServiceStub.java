package br.com.mbragariano.gostoreapi.contexts.product.implementation.services.stubs;

import br.com.mbragariano.gostoreapi.contexts.product.core.entities.ProductEntity;
import br.com.mbragariano.gostoreapi.contexts.product.core.vos.DescriptionVo;
import br.com.mbragariano.gostoreapi.contexts.product.core.vos.NameVo;
import br.com.mbragariano.gostoreapi.contexts.product.core.vos.PriceVo;
import br.com.mbragariano.gostoreapi.contexts.product.implementation.dtos.RegisterProductDto;
import org.bson.types.ObjectId;

import java.math.BigDecimal;

public class RegisterProductServiceStub {

  public static RegisterProductDto getRegisterProductDto() {
    return RegisterProductDto.builder()
      .name("Sabonete Nivea")
      .price(new BigDecimal("10.90"))
      .description("Novo sabonete nivea desenvolvido a base de seiva")
      .build();
  }

  public static RegisterProductDto getInvalidRegisterProductDto() {
    return RegisterProductDto.builder()
      .name(null)
      .price(null)
      .description(null)
      .build();
  }

  public static ProductEntity getProductEntity() {
    final var id = new ObjectId().toHexString();
    final var nameVo = new NameVo("Sabonete Nivea");
    final var priceVo = new PriceVo(new BigDecimal("10.90"));
    final var descriptionVo = new DescriptionVo("Novo sabonete nivea desenvolvido a base de seiva");

    return new ProductEntity(id, nameVo, priceVo, descriptionVo);
  }

}

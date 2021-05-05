package br.com.mbragariano.gostoreapi.contexts.product.core.entities;

import br.com.mbragariano.gostoreapi.commons.core.entity.BaseEntity;
import br.com.mbragariano.gostoreapi.contexts.product.core.vos.ProductDescriptionVo;
import br.com.mbragariano.gostoreapi.contexts.product.core.vos.ProductNameVo;
import br.com.mbragariano.gostoreapi.contexts.product.core.vos.ProductPriceVo;
import io.vavr.Value;
import io.vavr.control.Validation;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@ToString
@EqualsAndHashCode(callSuper = true)
public class ProductEntity extends BaseEntity {

  private final ProductNameVo name;

  private final ProductPriceVo price;

  private final ProductDescriptionVo description;

  public ProductEntity(
    final String id,
    final ProductNameVo name,
    final ProductPriceVo price,
    final ProductDescriptionVo description
  ) {
    super(id);

    this.name = name;
    this.price = price;
    this.description = description;
  }

  public String getNameValue() {
    return this.name.getValue();
  }

  public BigDecimal getPriceValue() {
    return this.price.getValue();
  }

  public String getDescriptionValue() {
    return this.description.getValue();
  }

  public static Validation<List<String>, ProductEntity> create(
    final String name,
    final BigDecimal price,
    final String description
  ) {
    return ProductNameVo.create(name)
      .combine(ProductPriceVo.create(price))
      .combine(ProductDescriptionVo.create(description))
      .ap((productNameVo, priceVo, productDescriptionVo) ->
        new ProductEntity(null, productNameVo, priceVo, productDescriptionVo))
      .mapError(Value::toJavaList);
  }

}

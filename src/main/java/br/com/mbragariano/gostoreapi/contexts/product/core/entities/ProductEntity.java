package br.com.mbragariano.gostoreapi.contexts.product.core.entities;

import br.com.mbragariano.gostoreapi.commons.core.entity.BaseEntity;
import br.com.mbragariano.gostoreapi.contexts.product.core.vos.DescriptionVo;
import br.com.mbragariano.gostoreapi.contexts.product.core.vos.NameVo;
import br.com.mbragariano.gostoreapi.contexts.product.core.vos.PriceVo;
import io.vavr.Value;
import io.vavr.control.Validation;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@ToString
@EqualsAndHashCode(callSuper = true)
public class ProductEntity extends BaseEntity {

  private final NameVo name;

  private final PriceVo price;

  private final DescriptionVo description;

  public ProductEntity(
    final String id,
    final NameVo name,
    final PriceVo price,
    final DescriptionVo description
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
    return NameVo.create(name)
      .combine(PriceVo.create(price))
      .combine(DescriptionVo.create(description))
      .ap((nameVo, priceVo, descriptionVo) ->
        new ProductEntity(null, nameVo, priceVo, descriptionVo))
      .mapError(Value::toJavaList);
  }

}

package jdbc;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;

@Table("coffee_makers")
@Getter
class CoffeeMaker {
  @Id
  private final short id;
  @Embedded.Empty
  private final Product product;
  private final CoffeeMakerType coffeeMakerType;

  CoffeeMaker(short id, Product product, CoffeeMakerType coffeeMakerType) {
    this.id = id;
    this.product = product;
    this.coffeeMakerType = coffeeMakerType;
  }
}
package jdbc;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;

@Table("coffee_drinks")
@Getter
class CoffeeDrink {
  @Id
  private final short id;
  @Embedded.Empty
  private final Product product;

  CoffeeDrink(short id, Product product) {
    this.id = id;
    this.product = product;
  }
}
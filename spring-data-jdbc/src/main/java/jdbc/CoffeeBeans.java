package jdbc;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;

@Table("coffee_beans")
@Getter
class CoffeeBeans {
  @Id
  private final short id;
  @Embedded.Empty
  private final Product product;
  private final CoffeeBeanType coffeeBeanType;

  CoffeeBeans(short id, Product product, CoffeeBeanType coffeeBeanType) {
    this.id = id;
    this.product = product;
    this.coffeeBeanType = coffeeBeanType;
  }
}

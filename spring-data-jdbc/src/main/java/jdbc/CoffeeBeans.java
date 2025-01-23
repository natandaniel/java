package jdbc;

import lombok.Getter;
import org.springframework.data.relational.core.mapping.Table;

@Table("coffee_beans")
@Getter
class CoffeeBeans extends Product {
  private final short weight;
  private final CoffeeBeanType coffeeBeanType;
  private final String supplierId;

  CoffeeBeans(short id, String name, short weight, CoffeeBeanType coffeeBeanType, float price,
      String supplierId) {
    super(id, name, price);
    this.supplierId = supplierId;
    this.weight = weight;
    this.coffeeBeanType = coffeeBeanType;
  }
}

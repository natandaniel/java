package jdbc;

import lombok.Getter;
import org.springframework.data.relational.core.mapping.Table;

@Table("coffee_maker")
class CoffeeMaker extends Product {
  private final CoffeeMakerType coffeeMakerType;
  @Getter
  private final String supplierName;

  CoffeeMaker(short id, String name, CoffeeMakerType coffeeMakerType, float price,
      String supplierName) {
    super(id, name, price);
    this.coffeeMakerType = coffeeMakerType;
    this.supplierName = supplierName;
  }
}
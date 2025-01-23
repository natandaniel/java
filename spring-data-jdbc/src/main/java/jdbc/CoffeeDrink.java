package jdbc;

import lombok.Getter;
import org.springframework.data.relational.core.mapping.Table;

@Table("coffee_drink")
class CoffeeDrink extends Product {
  @Getter
  private final String description;

  CoffeeDrink(short id, String name, String description, float price) {
    super(id, name, price);
    this.description = description;
  }
}
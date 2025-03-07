package jpa.examples.coffee_house;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "coffee_drink")
@Data
class CoffeeDrink {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Embedded
  private Product product;
  @ManyToOne
  @JoinColumn(name = "coffee_house", nullable = false)
  private CoffeeHouse coffeeHouse;

  protected CoffeeDrink() {}
}
package jpa.examples.coffee_house;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity(name = "coffee_maker")
@Data
class CoffeeMaker {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Embedded
  private Product product;
  @Enumerated(EnumType.STRING)
  @Column(name = "coffee_maker_type")
  private CoffeeMakerType coffeeMakerType;
  @Column(name = "stock_level")
  private int stockLevel;
  @Column(name = "last_updated")
  private LocalDateTime lastUpdated;
  @ManyToOne
  @JoinColumn(name = "coffee_house", nullable = false)
  private CoffeeHouse coffeeHouse;
}
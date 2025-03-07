package jpa.examples.coffee_house;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Entity(name = "coffee_beans")
@Data
@AllArgsConstructor
class CoffeeBeans {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Embedded
  private Product product;
  @Enumerated(EnumType.STRING)
  @Column(name = "coffee_bean_type")
  private CoffeeBeanType coffeeBeanType;
  @Column(name = "stock_level")
  private int stockLevel;
  @Column(name = "last_updated")
  private LocalDateTime lastUpdated;
  @ManyToOne
  @JoinColumn(name = "coffee_house", nullable = false)
  private CoffeeHouse coffeeHouse;

  protected CoffeeBeans() {}
}

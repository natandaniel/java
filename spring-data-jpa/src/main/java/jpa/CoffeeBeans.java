package jpa;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "coffee_beans")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@With(AccessLevel.PACKAGE)
class CoffeeBeans {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private final Integer id;
  @Embedded
  private final Product product;
  @Enumerated(EnumType.STRING)
  @Column(name = "coffee_bean_type")
  private final CoffeeBeanType coffeeBeanType;
  @Column(name = "stock_level")
  private final int stockLevel;
  @Column(name = "last_updated")
  private final LocalDateTime lastUpdated;
  @ManyToOne
  @JoinColumn(name = "coffee_house", nullable = false)
  private final CoffeeHouse coffeeHouse;
}

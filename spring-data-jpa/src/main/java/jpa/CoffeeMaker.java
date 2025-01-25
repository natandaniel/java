package jpa;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.data.relational.core.mapping.Embedded;

import java.time.LocalDateTime;

@Table(name = "coffee_maker")
@Entity
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@With(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
class CoffeeMaker {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private final Integer id;
  @Embedded.Empty
  private final Product product;
  @Enumerated(EnumType.STRING)
  @Column(name = "coffee_maker_type")
  private final CoffeeMakerType coffeeMakerType;
  @Column(name = "stock_level")
  private final int stockLevel;
  @Column(name = "last_updated")
  private final LocalDateTime lastUpdated;
  @ManyToOne
  @JoinColumn(name = "coffee_house", nullable = false)
  private final CoffeeHouse coffeeHouse;
}
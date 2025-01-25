package jpa;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Embedded;

@Table(name = "coffee_drink")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
class CoffeeDrink {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private final Integer id;
  @Embedded.Empty
  private final Product product;
  @ManyToOne
  @JoinColumn(name = "coffee_house", nullable = false)
  private final CoffeeHouse coffeeHouse;
}
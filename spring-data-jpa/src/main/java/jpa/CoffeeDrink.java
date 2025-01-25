package jpa;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.relational.core.mapping.Embedded;

@Table(name = "coffee_drink")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@With(AccessLevel.PRIVATE)
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
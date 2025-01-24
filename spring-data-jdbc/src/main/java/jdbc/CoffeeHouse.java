package jdbc;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
class CoffeeHouse {
  // managed entities
  @MappedCollection
  @With(AccessLevel.PRIVATE)
  private final Set<CoffeeBeans> managedCoffeeBeans;
  @MappedCollection
  @With(AccessLevel.PRIVATE)
  private final Set<CoffeeMaker> managedCoffeeMakers;
  @MappedCollection
  @With(AccessLevel.PRIVATE)
  private final Set<CoffeeDrink> managedCoffeeDrinks;
  @MappedCollection
  @With(AccessLevel.PRIVATE)
  private final Set<Sale> sales;

  // coffee house details
  @Id
  private int id;
  private String name;
  @Embedded.Empty
  private Address address;

  // referenced aggregates
  private AggregateReference<Supplier, Integer> supplier;

  private CoffeeHouse() {
    managedCoffeeBeans = new HashSet<>();
    managedCoffeeMakers = new HashSet<>();
    managedCoffeeDrinks = new HashSet<>();
    sales = new HashSet<>();
  }

  public void addCoffeeBeans(int id, Product product, CoffeeBeanType type, int stockLevel) {
    managedCoffeeBeans.add(new CoffeeBeans(id, product, type, stockLevel, LocalDateTime.now()));
  }

}

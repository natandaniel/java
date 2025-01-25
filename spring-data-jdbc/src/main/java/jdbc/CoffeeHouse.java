package jdbc;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
class CoffeeHouse {
  // managed entities
  @MappedCollection(keyColumn = "name")
  @With(AccessLevel.PRIVATE)
  private final Map<String, CoffeeBeans> managedCoffeeBeans;

  @MappedCollection(keyColumn = "name")
  @With(AccessLevel.PRIVATE)
  private final Map<String, CoffeeMaker> managedCoffeeMakers;

  @MappedCollection(keyColumn = "name")
  @With(AccessLevel.PRIVATE)
  private final Map<String, CoffeeDrink> managedCoffeeDrinks;

  @MappedCollection(keyColumn = "id")
  @With(AccessLevel.PRIVATE)
  private final List<Sale> sales;

  // coffee house details
  @Id
  @With
  private final int id;

  @With
  @Getter
  private final String name;

  @With
  @Getter
  @Embedded.Empty
  private final Address address;

  // referenced aggregates
  private AggregateReference<Supplier, Integer> supplier;

  private CoffeeHouse() {
    id = 0;
    name = "";
    address = null;

    managedCoffeeBeans = new HashMap<>();
    managedCoffeeMakers = new HashMap<>();
    managedCoffeeDrinks = new HashMap<>();
    sales = new ArrayList<>();
  }

  public Optional<CoffeeBeans> getCoffeeBeans(String name) {
    return Optional.ofNullable(managedCoffeeBeans.get(name));
  }

  public Optional<Sale> getLatestSale() {
    return Optional.ofNullable(sales.getLast());
  }

  public void addCoffeeBeans(Product product, CoffeeBeanType type, int stockLevel) {
    managedCoffeeBeans.put(product.name(), new CoffeeBeans(0, product, type, stockLevel,
        LocalDateTime.now()));
  }

  public void restockCoffeeBeans(String name, int quantity) {
    CoffeeBeans coffeeBeans = managedCoffeeBeans.get(name);
    if (coffeeBeans == null)
      throw new IllegalArgumentException("unknown product name: " + name);

    managedCoffeeBeans.put(name,
        new CoffeeBeans(coffeeBeans.id(), coffeeBeans.product(), coffeeBeans.coffeeBeanType(),
            coffeeBeans.stockLevel() + quantity, LocalDateTime.now()));
  }

  public void sellCoffeeBeans(String name, int quantity) {
    CoffeeBeans coffeeBeans = managedCoffeeBeans.get(name);
    if (coffeeBeans == null)
      throw new IllegalArgumentException("unknown product name: " + name);

    int stockLevel = coffeeBeans.stockLevel();
    if (stockLevel < quantity) throw new IllegalArgumentException("not enough stock");

    Sale sale = new Sale(sales.size() + 1, coffeeBeans.product().name(), quantity,
        quantity * coffeeBeans.product().price(),
        LocalDateTime.now());
    sales.add(sale);

    managedCoffeeBeans.put(name,
        new CoffeeBeans(coffeeBeans.id(), coffeeBeans.product(), coffeeBeans.coffeeBeanType(),
            stockLevel - quantity, LocalDateTime.now()));
  }

}

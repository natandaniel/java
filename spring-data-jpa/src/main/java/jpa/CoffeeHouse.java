package jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Embedded;

import java.time.LocalDateTime;
import java.util.*;

@Table(name = "coffee_house")
@Entity
@NoArgsConstructor(force = true)
class CoffeeHouse {
  // managed entities
  @OneToMany(mappedBy = "coffeeHouse", cascade = CascadeType.ALL, orphanRemoval = true, fetch =
      FetchType.EAGER)
  @MapKeyColumn(name = "name")
  private final Map<String, CoffeeBeans> managedCoffeeBeans;

  @OneToMany(mappedBy = "coffeeHouse", cascade = CascadeType.ALL, orphanRemoval = true, fetch =
      FetchType.EAGER)
  @MapKeyColumn(name = "name")
  private final Map<String, CoffeeMaker> managedCoffeeMakers;

  @OneToMany(mappedBy = "coffeeHouse", cascade = CascadeType.ALL, orphanRemoval = true, fetch =
      FetchType.EAGER)
  @MapKeyColumn(name = "name")
  private final Map<String, CoffeeDrink> managedCoffeeDrinks;

  @OneToMany(mappedBy = "coffeeHouse", cascade = CascadeType.ALL, orphanRemoval = true, fetch =
      FetchType.EAGER)
  @MapKeyColumn(name = "name")
  private final List<Sale> sales;

  // coffee house details
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  private final Integer id;

  @Getter
  private final String name;

  @Getter
  @Embedded.Empty
  private final Address address;

  // referenced aggregates
  @Getter
  private final int supplier;

  CoffeeHouse(String name, Address address, int supplier) {
    id = null;
    this.name = name;
    this.address = address;
    this.supplier = supplier;
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
    managedCoffeeBeans.put(product.name(),
        new CoffeeBeans(null, product, type, stockLevel, LocalDateTime.now(), this));
  }

  public void restockCoffeeBeans(String name, int quantity) {
    CoffeeBeans coffeeBeans = managedCoffeeBeans.get(name);
    if (coffeeBeans == null)
      throw new IllegalArgumentException("unknown product name: " + name);

    managedCoffeeBeans.put(name,
        coffeeBeans.withStockLevel(coffeeBeans.getStockLevel() + quantity));
  }

  public void sellCoffeeBeans(String name, int quantity) {
    CoffeeBeans coffeeBeans = managedCoffeeBeans.get(name);
    if (coffeeBeans == null)
      throw new IllegalArgumentException("unknown product name: " + name);

    int stockLevel = coffeeBeans.getStockLevel();
    if (stockLevel < quantity) throw new IllegalArgumentException("not enough stock");

    Sale sale = new Sale(null, coffeeBeans.getProduct().name(), quantity,
        quantity * coffeeBeans.getProduct().price(), LocalDateTime.now(), this);
    sales.add(sale);

    managedCoffeeBeans.put(name,
        coffeeBeans.withStockLevel(coffeeBeans.getStockLevel() - quantity));
  }

}

package jpa.examples.coffee_house;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.*;

@Entity(name = "coffee_house")
@Data
class CoffeeHouse {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;
  @Embedded
  private Address address;
  private int supplier;
  @OneToMany(mappedBy = "coffeeHouse", cascade = CascadeType.ALL, orphanRemoval = true)
  @MapKeyColumn(name = "name")
  private Map<String, CoffeeBeans> managedCoffeeBeans = new HashMap<>();
  @OneToMany(mappedBy = "coffeeHouse", cascade = CascadeType.ALL, orphanRemoval = true)
  @MapKeyColumn(name = "name")
  private Map<String, CoffeeMaker> managedCoffeeMakers = new HashMap<>();
  @OneToMany(mappedBy = "coffeeHouse", cascade = CascadeType.ALL, orphanRemoval = true)
  @MapKeyColumn(name = "name")
  private Map<String, CoffeeDrink> managedCoffeeDrinks = new HashMap<>();
  @OneToMany(mappedBy = "coffeeHouse", cascade = CascadeType.ALL, orphanRemoval = true)
  @MapKeyColumn(name = "name")
  private List<Sale> sales = new ArrayList<>();

  protected CoffeeHouse() {}

  CoffeeHouse(String name, Address address, int supplier) {
    id = null;
    this.name = name;
    this.address = address;
    this.supplier = supplier;
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

    coffeeBeans.setStockLevel(coffeeBeans.getStockLevel() + quantity);
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

    coffeeBeans.setStockLevel(coffeeBeans.getStockLevel() - quantity);
  }

}

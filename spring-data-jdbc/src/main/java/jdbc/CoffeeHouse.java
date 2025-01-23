package jdbc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("coffee_house")
@AllArgsConstructor
class CoffeeHouse {
  @Id
  private final short id;
  @Getter
  private final String name;
  private final String street;
  private final String city;
  private final String state;
  private final String zip;
  private final Inventory inventory;
  private final Sales sales;

  int getTotalSales(Short productId) {
    return 0;//sales.getTotalSales(productId);
  }

  void sellProduct(Short productId, int quantity) {
    // inventory.reduceStock(productId, quantity);
    //sales.recordSale(productId, quantity);
  }

  int getStock(Short productId) {
    return 0;// inventory.getStock(productId);
  }

  void addStock(Short productId, int quantity) {
    //inventory.addStock(productId, quantity);
  }

}

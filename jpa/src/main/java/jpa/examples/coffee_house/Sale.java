package jpa.examples.coffee_house;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Entity(name = "sale")
@Data
@AllArgsConstructor
class Sale {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(name = "product_name")
  private String productName;
  private int quantity;
  @Column(name = "total_price")
  private float totalPrice;
  @Column(name = "sale_date")
  private LocalDateTime saleDate;
  @ManyToOne
  @JoinColumn(name = "coffee_house", nullable = false)
  private CoffeeHouse coffeeHouse;

  protected Sale() {}
}
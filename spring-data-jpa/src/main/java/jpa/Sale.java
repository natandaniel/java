package jpa;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "sale")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class Sale {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private final Integer id;
  @Column(name = "product_name")
  private final String productName;
  private final int quantity;
  @Column(name = "total_price")
  private final float totalPrice;
  @Column(name = "sale_date")
  private final LocalDateTime saleDate;
  @ManyToOne
  @JoinColumn(name = "coffee_house", nullable = false)
  private final CoffeeHouse coffeeHouse;
}
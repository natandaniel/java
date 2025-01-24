package jdbc;

import lombok.*;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true) // required for JDBC mapping
@With
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
class Product {
  private final String name;
  private final float price;

  // optional
  private String description;
  private Float weight;
  private AggregateReference<Supplier, Short> supplierId;

  static ProductBuilder builder(String name, float price) {
    return new ProductBuilder(name, price);
  }

  @RequiredArgsConstructor
  static class ProductBuilder {
    private final String name;
    private final float price;

    // optional
    private String description;
    private Float weight;
    private Short supplierId;

    ProductBuilder setDescription(String description) {
      this.description = description;
      return this;
    }

    ProductBuilder setWeight(Float weight) {
      this.weight = weight;
      return this;
    }

    ProductBuilder setSupplierId(Short supplierId) {
      this.supplierId = supplierId;
      return this;
    }

    Product build() {
      Product product = new Product(name, price);
      product.description = this.description;
      product.weight = this.weight;
      product.supplierId = AggregateReference.to(supplierId);
      return product;
    }

  }

}
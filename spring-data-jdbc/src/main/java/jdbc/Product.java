package jdbc;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
class Product {
  private final String name;
  private final float price;

  // optional
  private String description;
  private Float weight;
  private Short supplierId;

  static ProductBuilder builder(String name, float price) {
    return new ProductBuilder(name, price);
  }

  private void setDescription(String description) {
    this.description = description;
  }

  private void setSupplierId(Short supplierId) {
    this.supplierId = supplierId;
  }

  private void setWeight(Float weight) {
    this.weight = weight;
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
      product.setDescription(description);
      product.setWeight(weight);
      product.setSupplierId(supplierId);
      return product;
    }

  }

}
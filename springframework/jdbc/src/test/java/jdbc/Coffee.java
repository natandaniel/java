package jdbc;

class Coffee {
  private final String name;
  private final float price;

  Coffee(String name, float price) {
    this.name = name;
    this.price = price;
  }

  String getName() {
    return name;
  }

  float getPrice() {
    return price;
  }
}

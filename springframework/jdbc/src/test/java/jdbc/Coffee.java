package jdbc;

public class Coffee {
  private final String name;
  private final float price;

  public Coffee(String name, float price) {
    this.name = name;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public float getPrice() {
    return price;
  }
}

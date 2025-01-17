package jdbc;

public class Coffee {
  private final String name;
  private final int supId;
  private final float price;
  private final int sales;
  private final int total;

  public Coffee(String name, int supId, float price, int sales, int total) {
    this.name = name;
    this.supId = supId;
    this.price = price;
    this.sales = sales;
    this.total = total;
  }

  public String getName() {
    return name;
  }

  public float getPrice() {
    return price;
  }

  public int getSupId() {
    return supId;
  }

  public int getSales() {
    return sales;
  }

  public int getTotal() {
    return total;
  }
}

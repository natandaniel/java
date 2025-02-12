package structural.decorator;

public class CoffeeDrink implements Drink {
  @Override
  public String getDescription() {
    return "Black";
  }

  @Override
  public double getCost() {
    return 2.0;
  }
}
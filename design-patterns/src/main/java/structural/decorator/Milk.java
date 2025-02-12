package structural.decorator;

public class Milk extends DrinkDecorator {

  public Milk(Drink drink) {
    super(drink);
  }

  @Override
  public String getDescription() {
    return drink.getDescription() + ", with milk";
  }

  @Override
  public double getCost() {
    return drink.getCost() + 0.5;
  }
}

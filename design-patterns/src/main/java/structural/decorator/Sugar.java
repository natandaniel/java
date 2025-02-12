package structural.decorator;

public class Sugar extends DrinkDecorator {

  public Sugar(Drink drink) {
    super(drink);
  }

  @Override
  public String getDescription() {
    return drink.getDescription() + ", with sugar";
  }

  @Override
  public double getCost() {
    return drink.getCost() + 0.2;
  }
}
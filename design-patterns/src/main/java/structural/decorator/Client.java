package structural.decorator;

public class Client {
  public static void main(String[] args) {
    Drink simpleCoffeeDrink = new CoffeeDrink();
    System.out.println(
        simpleCoffeeDrink.getDescription() + " -> Price: " + simpleCoffeeDrink.getCost() + "€");

    Drink coffeeDrinkWithMilk = new Milk(simpleCoffeeDrink);
    System.out.println(
        coffeeDrinkWithMilk.getDescription() + " -> Price: " + coffeeDrinkWithMilk.getCost() + "€");

    Drink coffeeDrinkWithMilkWithSugar = new Sugar(coffeeDrinkWithMilk);
    System.out.println(coffeeDrinkWithMilkWithSugar.getDescription() + " -> Price: " +
        coffeeDrinkWithMilkWithSugar.getCost() + "€");

    Drink coffeeDrinkWithDoubleMilkAndSugar = new Sugar(new Milk(new Milk(simpleCoffeeDrink)));
    System.out.println(coffeeDrinkWithDoubleMilkAndSugar.getDescription() + " -> Price: " +
        coffeeDrinkWithDoubleMilkAndSugar.getCost() + "€");
  }
}
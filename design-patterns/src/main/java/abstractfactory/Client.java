package abstractfactory;

public class Client {
  public static void main(String[] args) {
    CarOwner bob = new CarOwner("Bob", BmwCarFactory.getInstance());
    CarOwner tim = new CarOwner("Tim", MercedesCarFactory.getInstance());

    System.out.println(bob.toString());
    System.out.println(tim.toString());
  }
}
